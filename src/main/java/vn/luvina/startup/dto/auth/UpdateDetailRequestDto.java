package vn.luvina.startup.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateDetailRequestDto {

  @ApiModelProperty(value = "name", example = "Canh")
  @NotBlank(message = "Tên không được trống.")
  @Size(message = "Tên tối đa 40 kí tự.", max = 40)
  private String name;

  @ApiModelProperty(value = "avatarPath", example = "/path/avatar.png")
  @Size(message = "Đường đẫn avatar tối đa 255 kí tự.", max = 255)
  private String avatarPath;

  @ApiModelProperty(value = "address", example = "Ha Noi")
  @Size(message = "Địa chỉ tối đa 100 kí tự.", max = 100)
  private String address;

  @ApiModelProperty(value = "phoneNumber", example = "0909123456")
  @Size(message = "Số điện thoại tối đa 11 kí tự.", max = 11)
  private String phoneNumber;

  @ApiModelProperty(value = "deliveryAddress", example = "Nghia Do - Cau Giay - Ha Noi")
  @Size(message = "Địa chỉ giao hàng tối đa 100 kí tự.", max = 100)
  private String deliveryAddress;

}
