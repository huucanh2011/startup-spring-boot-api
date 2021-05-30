package vn.luvina.startup.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterRequestDto {

  @Email(message = "Không đúng định dạng email.")
  @NotBlank(message = "Email không được trống.")
  private String email;

  @NotBlank(message = "Tên không được trống.")
  private String name;

  @NotBlank(message = "Mật khẩu không được trống.")
  private String password;

}
