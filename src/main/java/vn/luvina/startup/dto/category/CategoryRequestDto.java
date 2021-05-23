package vn.luvina.startup.dto.category;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryRequestDto {

  @ApiModelProperty(value = "name", example = "Áo sơ mi")
  @NotBlank(message = "Tên thể loại không được trống.")
  private String name;

}
