package vn.luvina.startup.dto.user;

import lombok.Data;

@Data
public class UserSearchRequestDto {

  private String q;

  private Integer page;

  private Integer limit;

  private String sortBy;

  private String orderBy;

}
