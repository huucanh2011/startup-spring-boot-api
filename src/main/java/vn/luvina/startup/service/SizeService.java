package vn.luvina.startup.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vn.luvina.startup.dto.msize.SizeResponseDto;

public interface SizeService {
  @Transactional(readOnly = true)
  List<SizeResponseDto> getAll();

  @Transactional(readOnly = true)
  SizeResponseDto getSize(String name);
}
