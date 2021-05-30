package vn.luvina.startup.service;

import java.util.UUID;

import vn.luvina.startup.dto.category.CategoryRequestDto;
import vn.luvina.startup.dto.category.CategoryResponseDto;
import vn.luvina.startup.dto.category.CategorySearchRequestDto;
import vn.luvina.startup.dto.category.CategorySearchResponseDto;
import vn.luvina.startup.model.Category;

public interface CategoryService {

  Category findByName(String name);

  Category findById(UUID id);

  CategorySearchResponseDto findAll(CategorySearchRequestDto categorySearchRequestDto);

  CategoryResponseDto findOne(UUID id);

  CategoryResponseDto create(CategoryRequestDto categoryRequestDto);

  CategoryResponseDto update(UUID id, CategoryRequestDto categoryRequestDto);

  void delete(UUID id);

}
