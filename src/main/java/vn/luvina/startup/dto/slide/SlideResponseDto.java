package vn.luvina.startup.dto.slide;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SlideResponseDto {
  @ApiModelProperty(value = "id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID id;

  @ApiModelProperty(value = "title", example = "Sản phẩm mùa hè 1")
  private String title;

  @ApiModelProperty(value = "link", example = "www.google.com")
  private String link;

  @ApiModelProperty(value = "image", example = "san-pham-mua-he-1")
  private String image;

  @ApiModelProperty(value = "isActive", example = "true")
  private boolean isActive;

  @ApiModelProperty(value = "entryDate", example = "2021-06-10T09:24:57.785Z")
  private LocalDateTime entryDate;

  @ApiModelProperty(value = "updateDate", example = "2021-06-10T09:24:57.785Z")
  private LocalDateTime updateDate;
}
