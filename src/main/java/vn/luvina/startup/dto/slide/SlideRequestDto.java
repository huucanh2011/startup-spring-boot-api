package vn.luvina.startup.dto.slide;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SlideRequestDto {

  @ApiModelProperty(value = "title", example = "Sản phẩm mùa hè 1")
  private String title;

  @ApiModelProperty(value = "link", example = "www.google.com")
  private String link;

  @ApiModelProperty(value = "image", example = "san-pham-mua-he-1.jpg")
  private String image;

}
