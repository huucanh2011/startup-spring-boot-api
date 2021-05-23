package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.category.CategoryRequestDto;
import vn.luvina.startup.dto.category.CategoryResponseDto;
import vn.luvina.startup.exception.ErrorMessages;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.Category;
import vn.luvina.startup.repository.CategoryRepository;
import vn.luvina.startup.service.CategoryService;
import vn.luvina.startup.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final ModelMapper modelMapper;

  @Override
  public Category findByName(String name) {
    return categoryRepository.findByName(name).orElse(null);
  }

  @Override
  public Category findById(UUID id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ServiceRuntimeException(HttpStatus.NOT_FOUND, ErrorMessages.ERR_CATEGORY_002));
  }

  @Override
  public List<CategoryResponseDto> getAll() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream().map(category -> modelMapper.map(category, CategoryResponseDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public CategoryResponseDto getOne(UUID id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      return modelMapper.map(category, CategoryResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, ErrorMessages.ERR_CATEGORY_002);
  }

  @Override
  public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
    if (findByName(categoryRequestDto.getName()) == null) {
      Category category = modelMapper.map(categoryRequestDto, Category.class);
      category.setSlug(StringUtils.toSlug(categoryRequestDto.getName()));
      Category categoryCreated = categoryRepository.saveAndFlush(category);
      return modelMapper.map(categoryCreated, CategoryResponseDto.class);
    }
    throw new ServiceRuntimeException(HttpStatus.BAD_REQUEST, ErrorMessages.ERR_CATEGORY_001);
  }

  @Override
  public CategoryResponseDto update(UUID id, CategoryRequestDto categoryRequestDto) {
    Category category = findById(id);
    category.setName(categoryRequestDto.getName());
    category.setSlug(StringUtils.toSlug(categoryRequestDto.getName()));
    Category categoryUpdated = categoryRepository.saveAndFlush(category);
    return modelMapper.map(categoryUpdated, CategoryResponseDto.class);
  }

  @Override
  public void delete(UUID id) {
    Category category = findById(id);
    categoryRepository.delete(category);
  }

}
