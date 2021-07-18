package vn.luvina.startup.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import vn.luvina.startup.dto.user.UserRequestDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.dto.user.UserSearchRequestDto;
import vn.luvina.startup.dto.user.UserSearchResponseDto;
import vn.luvina.startup.strategy.sort.SortUserStrategy;

public interface UserServive {
  @Transactional
  void setSortUserStrategy(SortUserStrategy sortUserStrategy);

  @Transactional
  UserSearchResponseDto findAll(UserSearchRequestDto userSearchRequestDto);

  @Transactional
  UserResponseDto findById(UUID id);

  @Transactional
  UserResponseDto createUser(UserRequestDto userRequestDto);

  @Transactional
  UserResponseDto updateUser(UUID id,UserRequestDto userRequestDto);

  @Transactional
  void delete(UUID id);
}
