package vn.luvina.startup.strategy.sort;

import org.springframework.data.domain.Sort;

import vn.luvina.startup.enums.UserSortType;

public class SortUserByUpdateDateStrategy implements SortUserStrategy{
  
  @Override
  public Sort getSort() {
    return Sort.by(UserSortType.update_date.toString());
  }

}
