package vn.luvina.startup.dto.auth;

import lombok.Data;
import vn.luvina.startup.dto.user.UserResponseDto;

@Data
public class JwtResponseDto {

  private String token;

  private String type = "Bearer";

  private UserResponseDto user;

}
