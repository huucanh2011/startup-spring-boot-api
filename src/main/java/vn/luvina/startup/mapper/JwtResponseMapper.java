package vn.luvina.startup.mapper;

import org.springframework.stereotype.Component;

import vn.luvina.startup.dto.auth.JwtResponseDto;
import vn.luvina.startup.model.User;

@Component
public class JwtResponseMapper {

  public JwtResponseDto convertToJwtResponseDto(String token, User user) {
    JwtResponseDto jwtResponseDto = new JwtResponseDto();
    jwtResponseDto.setToken(token);
    jwtResponseDto.setId(user.getId());
    jwtResponseDto.setEmail(user.getEmail());
    jwtResponseDto.setName(user.getName());
    return jwtResponseDto;
  }

}
