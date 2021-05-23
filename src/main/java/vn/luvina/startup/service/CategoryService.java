package vn.luvina.startup.service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import vn.luvina.startup.dto.category.CategoryRequestDto;
import vn.luvina.startup.dto.category.CategoryResponseDto;
import vn.luvina.startup.model.Category;

public interface CategoryService {

  @Transactional(readOnly = true)
  Category findByName(String name);

  @Transactional(readOnly = true)
  Category findById(UUID id);

  @Transactional(readOnly = true)
  List<CategoryResponseDto> getAll();

  @Transactional(readOnly = true)
  CategoryResponseDto getOne(UUID id);

  @Transactional
  CategoryResponseDto create(CategoryRequestDto categoryRequestDto);

  @Transactional
  CategoryResponseDto update(UUID id, CategoryRequestDto categoryRequestDto);

  @Transactional
  void delete(UUID id);

}
