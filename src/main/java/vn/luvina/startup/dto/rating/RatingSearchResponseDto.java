package vn.luvina.startup.dto.rating;

import java.util.List;

import lombok.Data;
import vn.luvina.startup.dto.base.MetaResponse;
import vn.luvina.startup.model.Rating;

@Data
public class RatingSearchResponseDto {
  private List<Rating> data;

  private MetaResponse meta;
}
