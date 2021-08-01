package vn.luvina.startup.service;

import java.util.UUID;

import vn.luvina.startup.dto.user.CreateUserRequestDto;
import vn.luvina.startup.dto.user.UpdateRoleRequestDto;
import vn.luvina.startup.dto.user.UpdateUserRequestDto;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.dto.user.UserSearchRequestDto;
import vn.luvina.startup.dto.user.UserSearchResponseDto;
import vn.luvina.startup.strategy.sort.SortUserStrategy;

public interface UserServive {
  void setSortUserStrategy(SortUserStrategy sortUserStrategy);

  UserSearchResponseDto findAll(UserSearchRequestDto userSearchRequestDto);

  UserResponseDto findById(UUID id);

  UserResponseDto createUser(CreateUserRequestDto createUserRequestDto);

  UserResponseDto updateUser(UUID id, UpdateUserRequestDto updateUserRequestDto);

  void delete(UUID id);

  UserResponseDto updateUserRole(UUID id, UpdateRoleRequestDto updateRoleRequestDto);
}
