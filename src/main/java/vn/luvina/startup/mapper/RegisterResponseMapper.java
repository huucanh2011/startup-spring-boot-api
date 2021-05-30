package vn.luvina.startup.mapper;

import org.springframework.stereotype.Component;

import vn.luvina.startup.dto.auth.RegisterResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;

@Component
public class RegisterResponseMapper {

  public RegisterResponseDto convertToRegisterResponseDto(UserResponseDto userResponseDto) {
    RegisterResponseDto registerResponseDto = new RegisterResponseDto();
    registerResponseDto.setMessage("Đăng ký thành công.");
    registerResponseDto.setData(userResponseDto);
    return registerResponseDto;
  }

}
