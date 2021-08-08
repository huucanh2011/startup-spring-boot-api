package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.rating.RatingRequestDto;
import vn.luvina.startup.dto.rating.RatingResponseDto;
import vn.luvina.startup.dto.rating.RatingSearchRequestDto;
import vn.luvina.startup.dto.rating.RatingSearchResponseDto;
import vn.luvina.startup.dto.rating.UpdateStatusRatingRequestDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.mapper.MetaMapper;
import vn.luvina.startup.model.Rating;
import vn.luvina.startup.repository.RatingRepository;
import vn.luvina.startup.service.RatingService;
import vn.luvina.startup.util.AuthUtils;
import vn.luvina.startup.util.Constants;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
  private final RatingRepository ratingRepository;
  private final ModelMapper modelMapper;
  private final MetaMapper metaMapper;

  @Override
  public Rating findById(UUID id) {
    return ratingRepository.findById(id)
        .orElseThrow(() -> new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_RATING_001));
  }

  @Override
  public RatingSearchResponseDto getAll(RatingSearchRequestDto reqRating) {
    Integer reqLimit = reqRating.getLimit();
    Integer reqPage = reqRating.getPage();
    Integer limit = Constants.LIMIT_DEFAULT;
    Integer page = Constants.PAGE_DEFAULT;

    if (reqLimit != null && reqLimit.intValue() > 0) {
      limit = reqLimit;
    }
    if (reqPage != null && reqPage.intValue() > page) {
      page = reqPage;
    }

    Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("updateDate").descending());
    Page<Rating> pageRating = ratingRepository.findAll(pageable);
    List<Rating> listRating = pageRating.getContent();
    RatingSearchResponseDto ratingSearchResponseDto = new RatingSearchResponseDto();
    ratingSearchResponseDto.setData(listRating);
    ratingSearchResponseDto.setMeta(metaMapper.convertToMetaResponse(page, limit, pageRating));
    return ratingSearchResponseDto;
  }

  @Override
  public RatingResponseDto getOne(UUID id) {
    Rating rating = ratingRepository.findById(id).orElse(null);
    if (rating != null) {
      return modelMapper.map(rating, RatingResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_RATING_001);
  }

  @Override
  public RatingResponseDto create(RatingRequestDto ratingRequestDto) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Rating rating = modelMapper.map(ratingRequestDto, Rating.class);
    rating.setActive(true);
    rating.setUserId(AuthUtils.getUserDetailsImplFormContext().getId());
    Rating ratingCreated = ratingRepository.saveAndFlush(rating);
    return modelMapper.map(ratingCreated, RatingResponseDto.class);
  }

  @Override
  public RatingResponseDto update(UUID id, RatingRequestDto ratingRequestDto) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Rating rating = findById(id);
    if (rating.getId() != AuthUtils.getUserDetailsImplFormContext().getId()) {
      throw new ServiceRuntimeException(HttpStatus.UNAUTHORIZED, StartupMessages.ERR_COMMOM_001);
    }
    rating.setScores(ratingRequestDto.getScores());
    rating.setContent(ratingRequestDto.getContent());
    Rating ratingUpdated = ratingRepository.saveAndFlush(rating);
    return modelMapper.map(ratingUpdated, RatingResponseDto.class);
  }

  @Override
  public void delete(UUID id) {
    Rating rating = ratingRepository.findById(id).orElse(null);
    if (rating == null) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_RATING_001);
    } else {
      ratingRepository.deleteById(id);
    }
  }

  @Override
  public RatingSearchResponseDto getRatingByProduct(UUID productId, RatingSearchRequestDto reqRating) {
    Integer reqLimit = reqRating.getLimit();
    Integer reqPage = reqRating.getPage();
    Integer limit = Constants.LIMIT_DEFAULT;
    Integer page = Constants.PAGE_DEFAULT;
    if (reqLimit != null) {
      limit = reqLimit;
    }
    if (reqPage != null) {
      page = reqPage;
    }
    Pageable pageable = PageRequest.of(page - 1, limit);
    Page<Rating> pageRating = ratingRepository.findAllByProductIdAndIsActiveOrderByEntryDateDesc(productId, true,
        pageable);
    List<Rating> listRating = pageRating.getContent();
    RatingSearchResponseDto ratingSearchResponseDto = new RatingSearchResponseDto();
    ratingSearchResponseDto.setData(listRating);
    ratingSearchResponseDto.setMeta(metaMapper.convertToMetaResponse(page, limit, pageRating));
    return ratingSearchResponseDto;
  }

  @Override
  public RatingResponseDto updateState(UUID id, UpdateStatusRatingRequestDto updateStatusRatingRequestDto) {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Rating rating = ratingRepository.findById(id).orElse(null);
    if (rating == null) {
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_RATING_001);
    }
    rating.setActive(updateStatusRatingRequestDto.getIsActive());
    Rating ratingUpdatedState = ratingRepository.saveAndFlush(rating);
    return modelMapper.map(ratingUpdatedState, RatingResponseDto.class);
  }

}
