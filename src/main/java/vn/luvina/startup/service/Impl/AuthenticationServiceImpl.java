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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.auth.LoginRequestDto;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.dto.auth.UpdateDetailRequestDto;
import vn.luvina.startup.dto.auth.UpdatePasswordRequestDto;
import vn.luvina.startup.dto.base.MessageResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.mapper.JwtResponseMapper;
import vn.luvina.startup.mapper.RegisterUserMapper;
import vn.luvina.startup.model.User;
import vn.luvina.startup.repository.UserRepository;
import vn.luvina.startup.security.jwt.JwtUtils;
import vn.luvina.startup.security.services.UserDetailsImpl;
import vn.luvina.startup.service.AuthenticationService;
import vn.luvina.startup.service.UserMailService;
import vn.luvina.startup.util.AuthUtils;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  private final JwtResponseMapper jwtResponseMapper;

  private final RegisterUserMapper registerUserMapper;

  private final JwtUtils jwtUtils;

  private final UserMailService userMailService;

  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
    if (!userRepository.existsByEmail(registerRequestDto.getEmail().toLowerCase())) {
      User userCreated = userRepository.saveAndFlush(registerUserMapper.convertReqToUser(registerRequestDto));
      userMailService.sendMailRegister(userCreated.getEmail());
      return registerUserMapper.convertToRegisterResponseDto(modelMapper.map(userCreated, UserResponseDto.class));
    }
    throw new ServiceRuntimeException(HttpStatus.BAD_REQUEST, StartupMessages.ERR_AUTH_002);
  }

  @Override
  @Transactional(readOnly = true)
  public JwtResponseDto login(LoginRequestDto loginRequestDto) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail().toLowerCase(), loginRequestDto.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
      String jwtToken = jwtUtils.generateJwtToken(userDetailsImpl);
      User user = userRepository.findByEmail(loginRequestDto.getEmail()).get();
      return jwtResponseMapper.convertToJwtResponseDto(jwtToken, user);
    } catch (BadCredentialsException e) {
      throw new ServiceRuntimeException(HttpStatus.UNAUTHORIZED, StartupMessages.ERR_AUTH_001);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public JwtResponseDto me() {
    String bearerToken = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
        .getHeader("Authorization");
    String token = bearerToken.split(" ")[1];
    UserDetailsImpl userDetailsImpl = AuthUtils.getUserDetailsImplFormContext();
    User user = userRepository.findById(userDetailsImpl.getId()).get();
    return jwtResponseMapper.convertToJwtResponseDto(token, user);
  }

  @Override
  @Transactional
  public UserResponseDto updateDetail(UpdateDetailRequestDto updateDetailRequestDto) {
    UserDetailsImpl userDetailsImpl = AuthUtils.getUserDetailsImplFormContext();
    User user = userRepository.findById(userDetailsImpl.getId()).get();
    user.setName(updateDetailRequestDto.getName());
    user.setAvatarPath(updateDetailRequestDto.getAvatarPath());
    user.setAddress(updateDetailRequestDto.getAddress());
    user.setPhoneNumber(updateDetailRequestDto.getPhoneNumber());
    user.setDeliveryAddress(updateDetailRequestDto.getDeliveryAddress());
    User userUpdated = userRepository.saveAndFlush(user);
    return modelMapper.map(userUpdated, UserResponseDto.class);
  }

  @Override
  @Transactional
  public MessageResponseDto updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
    UserDetailsImpl userDetailsImpl = AuthUtils.getUserDetailsImplFormContext();
    User user = userRepository.findByEmail(userDetailsImpl.getUsername()).get();
    if (!checkIfValidOldPassword(user, updatePasswordRequestDto.getCurrentPassword())) {
      throw new ServiceRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, StartupMessages.ERR_AUTH_005);
    }
    user.setPassword(passwordEncoder.encode(updatePasswordRequestDto.getNewPassword()));
    userRepository.save(user);
    MessageResponseDto messageResponseDto = new MessageResponseDto();
    messageResponseDto.setMessage(StartupMessages.MSG_AUTH_003);
    return messageResponseDto;
  }

  private boolean checkIfValidOldPassword(User user, String currentPassword) {
    return passwordEncoder.matches(currentPassword, user.getPassword());
  }
}
