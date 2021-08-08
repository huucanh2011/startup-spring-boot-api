package vn.luvina.startup.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import vn.luvina.startup.dto.rating.RatingRequestDto;
import vn.luvina.startup.dto.rating.RatingResponseDto;
import vn.luvina.startup.dto.rating.RatingSearchRequestDto;
import vn.luvina.startup.dto.rating.RatingSearchResponseDto;
import vn.luvina.startup.dto.rating.UpdateStatusRatingRequestDto;
import vn.luvina.startup.model.Rating;

public interface RatingService {

  @Transactional(readOnly = true)
  Rating findById(UUID id);

  @Transactional(readOnly = true)
  RatingSearchResponseDto getAll(RatingSearchRequestDto reqRating);

  @Transactional(readOnly = true)
  RatingResponseDto getOne(UUID id);

  @Transactional
  RatingResponseDto create(RatingRequestDto ratingRequestDto);

  @Transactional
  RatingResponseDto update(UUID id, RatingRequestDto ratingRequestDto);

  @Transactional
  void delete(UUID id);

  @Transactional(readOnly = true)
  RatingSearchResponseDto getRatingByProduct(UUID productId, RatingSearchRequestDto reqRating);

  @Transactional
  RatingResponseDto updateState(UUID id, UpdateStatusRatingRequestDto updateStatusRatingRequestDto);
}
