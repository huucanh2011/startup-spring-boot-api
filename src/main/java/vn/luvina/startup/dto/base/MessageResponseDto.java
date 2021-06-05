package vn.luvina.startup.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageResponseDto {

  @ApiModelProperty(value = "message", example = "Đăng ký thành công.")
  private String message;

}
