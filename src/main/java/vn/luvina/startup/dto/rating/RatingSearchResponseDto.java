package vn.luvina.startup.dto.rating;

import java.util.List;

import lombok.Data;
import vn.luvina.startup.dto.base.MetaResponse;

@Data
public class RatingSearchResponseDto {
  private List<RatingResponseDto> data;

  private MetaResponse meta;
}
