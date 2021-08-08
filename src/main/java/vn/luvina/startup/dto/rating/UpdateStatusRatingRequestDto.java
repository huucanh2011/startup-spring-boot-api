package vn.luvina.startup.dto.rating;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateStatusRatingRequestDto {
  
  @ApiModelProperty(value = "isActive", example = "false")
  @NotNull(message = "Trạng thái không được bỏ trống.")
  private Boolean isActive;

}
