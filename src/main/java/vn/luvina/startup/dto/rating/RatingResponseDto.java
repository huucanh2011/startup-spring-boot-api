package vn.luvina.startup.dto.rating;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RatingResponseDto {
  @ApiModelProperty(value = "id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID id;

  @ApiModelProperty(value = "scores", example = "4")
  private Integer scores;

  @ApiModelProperty(value = "content", example = "Rất tốt")
  private String content;

  @ApiModelProperty(value = "isActive", example = "true")
  private boolean isActive;

  @ApiModelProperty(value = "productId", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID productId;

  @ApiModelProperty(value = "userId", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID userId;

  @ApiModelProperty(value = "entryDate", example = "2021-06-10T09:24:57.785Z")
  private LocalDateTime entryDate;

  @ApiModelProperty(value = "updateDate", example = "2021-06-10T09:24:57.785Z")
  private LocalDateTime updateDate;
}
