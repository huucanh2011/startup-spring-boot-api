package vn.luvina.startup.dto.category;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryResponseDto {

  @ApiModelProperty(value = "id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID id;

  @ApiModelProperty(value = "name", example = "Áo sơ mi")
  private String name;

  @ApiModelProperty(value = "slug", example = "ao-so-mi")
  private String slug;

  @ApiModelProperty(value = "entryDate", example = "2021-05-23T09:24:57.785Z")
  private LocalDateTime entryDate;

  @ApiModelProperty(value = "updateDate", example = "2021-05-23T09:24:57.785Z")
  private LocalDateTime updateDate;

}
