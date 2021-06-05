package vn.luvina.startup.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ForgotPasswordRequestDto {

  @ApiModelProperty(value = "email", example = "example@example.xyz")
  @NotBlank(message = "Email không được trống.")
  @Email(message = "Email không đúng định dạng.")
  @Size(message = "Email tối đa 60 kí tự.", max = 60)
  private String email;

}
