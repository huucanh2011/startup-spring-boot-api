package vn.luvina.startup.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.model.User;

@Component
@RequiredArgsConstructor
public class JwtResponseMapper {

  private final ModelMapper modelMapper;

  public JwtResponseDto convertToJwtResponseDto(String token, User user) {
    JwtResponseDto jwtResponseDto = new JwtResponseDto();
    jwtResponseDto.setToken(token);
    jwtResponseDto.setUser(modelMapper.map(user, UserResponseDto.class));
    return jwtResponseDto;
  }

}
