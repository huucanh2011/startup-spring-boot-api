package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.category.CategoryRequestDto;
import vn.luvina.startup.dto.category.CategoryResponseDto;
import vn.luvina.startup.dto.category.CategorySearchRequestDto;
import vn.luvina.startup.dto.category.CategorySearchResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.mapper.MetaMapper;
import vn.luvina.startup.model.Category;
import vn.luvina.startup.repository.CategoryRepository;
import vn.luvina.startup.service.CategoryService;
import vn.luvina.startup.util.Constants;
import vn.luvina.startup.util.StartupMessages;
import vn.luvina.startup.util.StringUtils;

@Service
@CacheConfig(cacheNames = "categories")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final ModelMapper modelMapper;

  private final MetaMapper metaMapper;

  @Override
  @Transactional(readOnly = true)
  public Category findByName(String name) {
    return categoryRepository.findByName(name).orElse(null);
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(key = "#id")
  public Category findById(UUID id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_CATEGORY_002));
  }

  @Override
  @Transactional(readOnly = true)
  // @Cacheable
  public CategorySearchResponseDto findAll(CategorySearchRequestDto categorySearchRequestDto) {
    String reqQ = categorySearchRequestDto.getQ();
    Integer reqPage = categorySearchRequestDto.getPage();
    Integer reqLimit = categorySearchRequestDto.getLimit();
    Integer pageNum = Constants.PAGE_DEFAULT;
    Integer pageLimit = Constants.LIMIT_DEFAULT;

    if (reqPage != null) {
      pageNum = reqPage;
    }
    if (reqLimit != null) {
      pageLimit = reqLimit;
    }

    Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.by("updateDate").descending().and(Sort.by("name")));

    Page<Category> page;

    if (reqQ == null || reqQ.toString().trim() == "") {
      page = categoryRepository.findAll(pageable);
    } else {
      page = categoryRepository.findAllByNameIgnoreCaseContainingOrSlugIgnoreCaseContaining(reqQ, reqQ, pageable);
    }

    List<Category> categories = page.getContent();

    CategorySearchResponseDto categorySearchResponseDto = new CategorySearchResponseDto();
    categorySearchResponseDto.setData(categories.stream()
        .map(category -> modelMapper.map(category, CategoryResponseDto.class)).collect(Collectors.toList()));
    categorySearchResponseDto.setMeta(metaMapper.convertToMetaResponse(pageNum, pageLimit, page));

    return categorySearchResponseDto;
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(key = "#id")
  public CategoryResponseDto findOne(UUID id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      return modelMapper.map(category.get(), CategoryResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_CATEGORY_002);
  }

  @Override
  @Transactional
  public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
    if (findByName(categoryRequestDto.getName()) == null) {
      Category category = modelMapper.map(categoryRequestDto, Category.class);
      category.setSlug(StringUtils.toSlug(categoryRequestDto.getName()));
      Category categoryCreated = categoryRepository.saveAndFlush(category);
      return modelMapper.map(categoryCreated, CategoryResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.BAD_REQUEST, StartupMessages.ERR_CATEGORY_001);
  }

  @Override
  @Transactional
  @CachePut(key = "#id")
  public CategoryResponseDto update(UUID id, CategoryRequestDto categoryRequestDto) {
    Category category = findById(id);
    category.setName(categoryRequestDto.getName());
    category.setSlug(StringUtils.toSlug(categoryRequestDto.getName()));
    Category categoryUpdated = categoryRepository.saveAndFlush(category);
    return modelMapper.map(categoryUpdated, CategoryResponseDto.class);
  }

  @Override
  @Transactional
  @CacheEvict(key = "#id")
  public void delete(UUID id) {
    Category category = findById(id);
    categoryRepository.delete(category);
  }

}
