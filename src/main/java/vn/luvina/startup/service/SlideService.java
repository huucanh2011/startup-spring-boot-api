package vn.luvina.startup.service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import vn.luvina.startup.dto.slide.SlideRequestDto;
import vn.luvina.startup.dto.slide.SlideResponseDto;
import vn.luvina.startup.dto.slide.UpdateStatusSlideRequestDto;

public interface SlideService {
  @Transactional(readOnly = true)
  List<SlideResponseDto> getAllSlide();

  @Transactional(readOnly = true)
  SlideResponseDto getSlide(UUID id);

  @Transactional
  SlideResponseDto create(SlideRequestDto slideRequestDto);

  @Transactional
  SlideResponseDto update(UUID id, SlideRequestDto slideRequestDto);

  @Transactional
  void delete(UUID id);

  @Transactional
  SlideResponseDto updateStatus(UUID id, UpdateStatusSlideRequestDto updateStatusSlideRequestDto);

}
