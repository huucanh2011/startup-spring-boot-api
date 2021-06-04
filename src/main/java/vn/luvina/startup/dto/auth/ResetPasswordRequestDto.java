package vn.luvina.startup.dto.auth;

import lombok.Data;

@Data
public class ResetPasswordRequestDto {

  private String email;

  private String password;

  private String resetToken;

}
