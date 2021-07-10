package vn.luvina.startup.dto.msize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SizeRequestDto {
  @ApiModelProperty(value = "name", example = "M")
  private String name;
}
