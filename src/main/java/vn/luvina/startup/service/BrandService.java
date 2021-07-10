package vn.luvina.startup.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vn.luvina.startup.dto.mbrand.BrandResponseDto;

public interface BrandService {
  @Transactional(readOnly = true)
  List<BrandResponseDto> getAll();

  @Transactional(readOnly = true)
  BrandResponseDto getBrand(String name);
}
