package vn.luvina.startup.dto.slide;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateStatusSlideRequestDto {

  @ApiModelProperty(value = "isActive", example = "false")
  @NotNull(message = "Trạng thái không được bỏ trống.")
  private Boolean isActive;

}
