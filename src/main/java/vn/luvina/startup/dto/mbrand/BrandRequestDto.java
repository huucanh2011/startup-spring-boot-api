package vn.luvina.startup.dto.mbrand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandRequestDto {
  @ApiModelProperty(value = "name", example = "adidas")
  private String name;
}
