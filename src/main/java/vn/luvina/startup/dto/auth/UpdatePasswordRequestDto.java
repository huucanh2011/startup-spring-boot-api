package vn.luvina.startup.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdatePasswordRequestDto {

  @ApiModelProperty(value = "password", example = "password")
  @NotBlank(message = "Mật khẩu cũ không được trống.")
  @Size(message = "Mật khẩu cũ từ 6 đến 60 kí tự.", min = 6, max = 60)
  private String currentPassword;

  @ApiModelProperty(value = "password", example = "password")
  @NotBlank(message = "Mật khẩu mới không được trống.")
  @Size(message = "Mật khẩu mới từ 6 đến 60 kí tự.", min = 6, max = 60)
  private String newPassword;

}
