package vn.luvina.startup.dto.rating;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RatingSearchRequestDto {
  @ApiModelProperty(name = "page", example = "2")
  private Integer page;
  @ApiModelProperty(name = "limit", example = "2")
  private Integer limit;
}
