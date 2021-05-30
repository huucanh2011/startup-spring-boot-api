package vn.luvina.startup.dto.base;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class MetaResponse {

  private Long totalCount;

  private Integer totalPages;

  private Integer currentPage;

  private Integer limit;

}
