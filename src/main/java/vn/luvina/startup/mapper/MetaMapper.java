package vn.luvina.startup.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import vn.luvina.startup.dto.base.MetaResponse;

@Component
public class MetaMapper {

  public MetaResponse convertToMetaResponse(Integer currentPage, Integer limit, Page<?> page) {
    MetaResponse metaResponse = new MetaResponse();
    metaResponse.setCurrentPage(currentPage);
    metaResponse.setLimit(limit);
    metaResponse.setTotalPages(page.getTotalPages());
    metaResponse.setTotalCount(page.getTotalElements());
    return metaResponse;
  }
}
