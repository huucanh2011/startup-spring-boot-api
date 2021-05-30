package vn.luvina.startup.dto.category;

import java.util.List;

import lombok.Data;
import vn.luvina.startup.dto.base.MetaResponse;

@Data
public class CategorySearchResponseDto {

  private List<CategoryResponseDto> data;

  private MetaResponse meta;

}
