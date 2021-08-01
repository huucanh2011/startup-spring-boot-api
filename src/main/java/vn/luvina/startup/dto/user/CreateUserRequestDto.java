package vn.luvina.startup.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateUserRequestDto extends UserRequestDto {
  @ApiModelProperty(value = "password", example = "password")
  @NotBlank(message = "Mật khẩu không được trống.")
  @Size(message = "Mật khẩu từ 6 đến 60 kí tự.", min = 6, max = 60)
  private String password;
}
