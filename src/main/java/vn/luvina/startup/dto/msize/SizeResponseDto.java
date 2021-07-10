package vn.luvina.startup.dto.msize;

import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SizeResponseDto {
  @ApiModelProperty(value = "id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID id;

  @ApiModelProperty(value = "name", example = "M")
  private String name;

  @ApiModelProperty(value = "value", example = "size M")
  private String value;

  @ApiModelProperty(value = "isShirt", example = "true")
  private boolean isShirt;
}
