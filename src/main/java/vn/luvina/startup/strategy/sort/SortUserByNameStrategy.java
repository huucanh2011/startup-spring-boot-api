package vn.luvina.startup.strategy.sort;

import org.springframework.data.domain.Sort;

import vn.luvina.startup.enums.UserSortType;

public class SortUserByNameStrategy implements SortUserStrategy {

  @Override
  public Sort getSort() {
    return Sort.by(UserSortType.name.toString());
  }

}
