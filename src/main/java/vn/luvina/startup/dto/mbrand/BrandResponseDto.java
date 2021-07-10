package vn.luvina.startup.dto.mbrand;

import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandResponseDto {
  
  @ApiModelProperty(value = "id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID id;

  @ApiModelProperty(value = "name", example = "Dolce&Gabbana")
  private String name;

  @ApiModelProperty(value = "slug", example = "Dolce-and-Gabbana")
  private String slug;
}
