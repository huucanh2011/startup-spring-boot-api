package vn.luvina.startup.controller.v1;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.category.CategoryRequestDto;
import vn.luvina.startup.dto.category.CategoryResponseDto;
import vn.luvina.startup.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  @ApiOperation("Lấy tất cả thể loại")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<List<CategoryResponseDto>> getCategories() {
    return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
  }

  @PostMapping
  @ApiOperation("Tạo mới thể loại")
  @ApiResponses({ @ApiResponse(code = 201, message = "") })
  public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
    return new ResponseEntity<>(categoryService.create(categoryRequestDto), HttpStatus.CREATED);
  }

  @PutMapping("/{categoryId}")
  @ApiOperation("Cập nhật thể loại")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable(value = "categoryId") UUID categoryId,
      @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
    return new ResponseEntity<>(categoryService.update(categoryId, categoryRequestDto), HttpStatus.OK);
  }

  @DeleteMapping("/{categoryId}")
  @ApiOperation("Xoá thể loại")
  @ApiResponses({ @ApiResponse(code = 204, message = "") })
  public ResponseEntity<?> deleteCategory(@PathVariable(value = "categoryId") UUID categoryId) {
    categoryService.delete(categoryId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
