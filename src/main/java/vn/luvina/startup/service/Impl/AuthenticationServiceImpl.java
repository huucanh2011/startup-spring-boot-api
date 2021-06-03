package vn.luvina.startup.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.auth.LoginRequestDto;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.enums.UserRole;
import vn.luvina.startup.enums.UserStatus;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.mapper.JwtResponseMapper;
import vn.luvina.startup.mapper.RegisterResponseMapper;
import vn.luvina.startup.model.User;
import vn.luvina.startup.repository.UserRepository;
import vn.luvina.startup.security.jwt.JwtUtils;
import vn.luvina.startup.security.services.UserDetailsImpl;
import vn.luvina.startup.service.AuthenticationService;
import vn.luvina.startup.service.UserMailService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  private final JwtResponseMapper jwtResponseMapper;

  private final RegisterResponseMapper registerResponseMapper;

  private final JwtUtils jwtUtils;

  private final PasswordEncoder passwordEncoder;

  private final UserMailService userMailService;

  @Override
  public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
    if (!userRepository.existsByEmail(registerRequestDto.getEmail())) {
      User user = new User();
      user.setName(registerRequestDto.getName());
      user.setEmail(registerRequestDto.getEmail());
      user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
      user.setRole(UserRole.USER.toString());
      user.setStatus(UserStatus.ACTIVED.toString());

      User userCreated = userRepository.saveAndFlush(user);

      userMailService.sendMailRegister(userCreated.getEmail());

      return registerResponseMapper.convertToRegisterResponseDto(modelMapper.map(userCreated, UserResponseDto.class));
    }

    throw new ServiceRuntimeException(HttpStatus.BAD_REQUEST, "Email đã được sử dụng.");

  }

  @Override
  public JwtResponseDto login(LoginRequestDto loginRequestDto) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

      String jwtToken = jwtUtils.generateJwtToken(userDetailsImpl);

      User user = userRepository.findByEmail(loginRequestDto.getEmail()).get();

      return jwtResponseMapper.convertToJwtResponseDto(jwtToken, user);

    } catch (BadCredentialsException e) {
      throw new ServiceRuntimeException(HttpStatus.UNAUTHORIZED, "Email hoặc mật khẩu không đúng.");
    }
  }

  @Override
  public JwtResponseDto me() {
    String bearerToken = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
        .getHeader("Authorization");
    String token = bearerToken.split(" ")[1];

    UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();

    User user = userRepository.findById(userDetailsImpl.getId()).get();

    return jwtResponseMapper.convertToJwtResponseDto(token, user);
  }

}
