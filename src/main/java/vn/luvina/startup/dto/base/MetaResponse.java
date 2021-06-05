package vn.luvina.startup.dto.base;

import javax.persistence.MappedSuperclass;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
public class MetaResponse {

  @ApiModelProperty(value = "totalCount", example = "100")
  private Long totalCount;

  @ApiModelProperty(value = "totalCount", example = "10")
  private Integer totalPages;

  @ApiModelProperty(value = "totalCount", example = "1")
  private Integer currentPage;

  @ApiModelProperty(value = "totalCount", example = "10")
  private Integer limit;

}
