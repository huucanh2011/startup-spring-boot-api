package vn.luvina.startup.dto.rating;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RatingRequestDto {

  @ApiModelProperty(value = "scores", example = "4")
  @NotNull(message = "Số điểm không được bỏ trống.")
  private Integer scores;

  @ApiModelProperty(value = "content", example = "Rất tốt")
  private String content;

  @ApiModelProperty(value = "userId", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID userId;
}
