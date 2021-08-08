package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.slide.SlideRequestDto;
import vn.luvina.startup.dto.slide.SlideResponseDto;
import vn.luvina.startup.dto.slide.UpdateStatusSlideRequestDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.Slide;
import vn.luvina.startup.repository.SlideRepository;
import vn.luvina.startup.service.SlideService;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {
  private final SlideRepository slideRespository;
  private final ModelMapper modelMapper;

  @Override
  public List<SlideResponseDto> getAllSlide() {
    List<Slide> listSlide = slideRespository.findAll();
    return listSlide.stream().map(slide -> modelMapper.map(slide, SlideResponseDto.class)).collect(Collectors.toList());
  }

  @Override
  public SlideResponseDto getSlide(UUID id) {
    Slide slide = slideRespository.findById(id).orElse(null);
    if (slide != null) {
      return modelMapper.map(slide, SlideResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_SLIDE_001);
  }

  @Override
  public SlideResponseDto create(SlideRequestDto slideRequestDto) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Slide slide = modelMapper.map(slideRequestDto, Slide.class);
    slide.setActive(true);
    Slide slideCreated = slideRespository.saveAndFlush(slide);
    return modelMapper.map(slideCreated, SlideResponseDto.class);
  }

  @Override
  public SlideResponseDto update(UUID id, SlideRequestDto slideRequestDto) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Slide slide = slideRespository.getById(id);
    if (slide == null) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_SLIDE_001);
    }
    slide.setTitle(slideRequestDto.getTitle());
    slide.setLink(slideRequestDto.getLink());
    slide.setImage(slideRequestDto.getImage());
    Slide slideUpdated = slideRespository.saveAndFlush(slide);
    return modelMapper.map(slideUpdated, SlideResponseDto.class);
  }

  @Override
  public void delete(UUID id) {
    Slide slide = slideRespository.getById(id);
    if (slide == null) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_SLIDE_001);
    } else {
      slideRespository.deleteById(id);
    }
  }

  @Override
  public SlideResponseDto updateStatus(UUID id, UpdateStatusSlideRequestDto updateStatusSlideRequestDto) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Slide slide = slideRespository.getById(id);
    if (slide == null) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_SLIDE_001);
    }
    slide.setActive(updateStatusSlideRequestDto.getIsActive());
    Slide slideUpdated = slideRespository.saveAndFlush(slide);
    return modelMapper.map(slideUpdated, SlideResponseDto.class);
  }
}
