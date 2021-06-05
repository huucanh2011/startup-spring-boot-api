package vn.luvina.startup.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.RegisterRequestDto;
import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.enums.UserRole;
import vn.luvina.startup.enums.UserStatus;
import vn.luvina.startup.model.User;
import vn.luvina.startup.util.StartupMessages;

@Component
@RequiredArgsConstructor
public class RegisterUserMapper {

  private final PasswordEncoder passwordEncoder;

  public User convertReqToUser(RegisterRequestDto registerRequestDto) {
    User user = new User();
    user.setName(registerRequestDto.getName());
    user.setEmail(registerRequestDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
    user.setRole(UserRole.USER.toString());
    user.setStatus(UserStatus.ACTIVED.toString());
    return user;
  }

  public RegisterResponseDto convertToRegisterResponseDto(UserResponseDto userResponseDto) {
    RegisterResponseDto registerResponseDto = new RegisterResponseDto();
    registerResponseDto.setMessage(StartupMessages.MSG_AUTH_001);
    registerResponseDto.setData(userResponseDto);
    return registerResponseDto;
  }

}
