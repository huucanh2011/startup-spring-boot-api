package vn.luvina.startup.controller.v1;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.dto.user.UserSearchRequestDto;
import vn.luvina.startup.dto.user.UserSearchResponseDto;
import vn.luvina.startup.enums.UserSortType;
import vn.luvina.startup.service.UserServive;
import vn.luvina.startup.strategy.sort.SortUserByEmailStrategy;
import vn.luvina.startup.strategy.sort.SortUserByEntryDateStrategy;
import vn.luvina.startup.strategy.sort.SortUserByNameStrategy;
import vn.luvina.startup.strategy.sort.SortUserByRoleStrategy;
import vn.luvina.startup.strategy.sort.SortUserByStatusStrategy;
import vn.luvina.startup.strategy.sort.SortUserByUpdateDateStrategy;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserServive userServive;

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<UserSearchResponseDto> getUsers(@RequestParam(required = false) String q, Integer page,
      Integer limit, String sortBy, String orderBy) {
    UserSearchRequestDto userSearchRequestDto = new UserSearchRequestDto();
    userSearchRequestDto.setQ(q);
    userSearchRequestDto.setPage(page);
    userSearchRequestDto.setLimit(limit);
    userSearchRequestDto.setSortBy(sortBy);
    userSearchRequestDto.setOrderBy(orderBy);
    if (sortBy != null) {
      if (UserSortType.name.toString().equals(sortBy)) {
        userServive.setSortUserStrategy(new SortUserByNameStrategy());
      } else if (UserSortType.email.toString().equals(sortBy)) {
        userServive.setSortUserStrategy(new SortUserByEmailStrategy());
      } else if (UserSortType.role.toString().equals(sortBy)) {
        userServive.setSortUserStrategy(new SortUserByRoleStrategy());
      } else if (UserSortType.status.toString().equals(sortBy)) {
        userServive.setSortUserStrategy(new SortUserByStatusStrategy());
      } else if (UserSortType.entry_date.toString().equals(sortBy)) {
        userServive.setSortUserStrategy(new SortUserByEntryDateStrategy());
      }
    }
    userServive.setSortUserStrategy(new SortUserByUpdateDateStrategy());
    return new ResponseEntity<>(userServive.findAll(userSearchRequestDto), HttpStatus.OK);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/{userId}")
  public ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "userId") UUID id) {
    return new ResponseEntity<>(userServive.findById(id), HttpStatus.OK);
  }

}