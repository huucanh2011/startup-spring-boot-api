package vn.luvina.startup.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequestDto {

  @ApiModelProperty(value = "email", example = "example@example.xyz")
  @NotBlank(message = "Email không được trống.")
  @Email(message = "Email không đúng định dạng.")
  @Size(message = "Email tối đa 60 kí tự.", max = 60)
  private String email;

  @ApiModelProperty(value = "password", example = "password")
  @NotBlank(message = "Mật khẩu không được trống.")
  @Size(message = "Mật khẩu từ 6 đến 60 kí tự.", min = 6, max = 60)
  private String password;

}
