package vn.luvina.startup.dto.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategorySearchRequestDto {

  @ApiModelProperty(name = "q", example = "Áo thun")
  private String q;

  @ApiModelProperty(name = "page", example = "2")
  private Integer page;

  @ApiModelProperty(name = "limit", example = "10")
  private Integer limit;

}
