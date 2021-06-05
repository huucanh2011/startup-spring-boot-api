package vn.luvina.startup.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponseDto {

  @ApiModelProperty
  private UUID id;

  @ApiModelProperty(value = "name", example = "Canh")
  private String name;

  @ApiModelProperty(value = "email", example = "example@example.xyz")
  private String email;

  @ApiModelProperty(value = "avatarPath", example = "https://example.com/img/avatar.png")
  private String avatarPath;

  @ApiModelProperty(value = "address", example = "Nghia Do, Cau Giay, Ha Noi")
  private String address;

  @ApiModelProperty(value = "phoneNumber", example = "0935000123")
  private String phoneNumber;

  @ApiModelProperty(value = "address", example = "28, Ngo 34, Nghia Do, Nghia Do, Cau Giay, Ha Noi")
  private String deliveryAddress;

  @ApiModelProperty(value = "role", example = "USER")
  private String role;

  @ApiModelProperty(value = "entryDate", example = "2021-05-23T09:24:57.785Z")
  private LocalDateTime entryDate;

  @ApiModelProperty(value = "updateDate", example = "2021-05-23T09:24:57.785Z")
  private LocalDateTime updateDate;

}
