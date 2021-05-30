package vn.luvina.startup.dto.auth;

import java.util.UUID;

import lombok.Data;

@Data
public class JwtResponseDto {

  private String token;

  private String type = "Bearer";

  private UUID id;

  private String email;

  private String name;

}
