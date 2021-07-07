package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.user.UserResponseDto;
import vn.luvina.startup.dto.user.UserSearchRequestDto;
import vn.luvina.startup.dto.user.UserSearchResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.mapper.MetaMapper;
import vn.luvina.startup.model.User;
import vn.luvina.startup.repository.UserRepository;
import vn.luvina.startup.service.UserServive;
import vn.luvina.startup.strategy.sort.SortUserStrategy;
import vn.luvina.startup.util.Constants;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServive {

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  private final MetaMapper metaMapper;

  private SortUserStrategy sortUserStrategy;

  @Override
  public void setSortUserStrategy(SortUserStrategy sortUserStrategy) {
    this.sortUserStrategy = sortUserStrategy;
  }

  @Override
  @Transactional(readOnly = true)
  public UserSearchResponseDto findAll(UserSearchRequestDto userSearchRequestDto) {
    String reqQ = userSearchRequestDto.getQ();
    Integer reqPage = userSearchRequestDto.getPage();
    Integer reqLimit = userSearchRequestDto.getLimit();
    String reqOrderBy = userSearchRequestDto.getOrderBy();
    Integer pageNum = Constants.PAGE_DEFAULT;
    Integer pageLimit = Constants.LIMIT_DEFAULT;
    String orderBy = Constants.ORDER_BY_DEFAULT;
    Integer pageNumDivOne;
    Pageable pageable;
    Sort sort;
    Page<User> page;
    List<User> users;

    if (reqPage != null && reqPage.intValue() > pageNum) {
      pageNum = reqPage;
    }
    if (reqLimit != null && reqLimit.intValue() > 0) {
      pageLimit = reqLimit;
    }
    if (reqOrderBy != null) {
      orderBy = reqOrderBy;
    }

    pageNumDivOne = pageNum - 1;
    sort = sortUserStrategy.getSort();

    if ("asc".equals(orderBy.toLowerCase())) {
      pageable = PageRequest.of(pageNumDivOne, pageLimit, sort.ascending());
    } else if ("desc".equals(orderBy.toLowerCase())) {
      pageable = PageRequest.of(pageNumDivOne, pageLimit, sort.descending());
    }

    pageable = PageRequest.of(pageNumDivOne, pageLimit);

    page = userRepository.findAll(pageable);

    if (reqQ != null && reqQ.toString().trim() != "") {
      page = userRepository.findAllByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(reqQ, reqQ, pageable);
    }

    users = page.getContent();

    UserSearchResponseDto userSearchResponseDto = new UserSearchResponseDto();
    userSearchResponseDto
        .setData(users.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList()));
    userSearchResponseDto.setMeta(metaMapper.convertToMetaResponse(pageNum, pageLimit, page));
    return userSearchResponseDto;
  }

  @Override
  @Transactional(readOnly = true)
  public UserResponseDto findById(UUID id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return modelMapper.map(user.get(), UserResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_USER_001);
  }

}
