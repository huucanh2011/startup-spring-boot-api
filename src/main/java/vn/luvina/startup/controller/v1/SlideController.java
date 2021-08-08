package vn.luvina.startup.controller.v1;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.slide.SlideRequestDto;
import vn.luvina.startup.dto.slide.SlideResponseDto;
import vn.luvina.startup.dto.slide.UpdateStatusSlideRequestDto;
import vn.luvina.startup.service.SlideService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/slides")
public class SlideController {
  private final SlideService slideService;

  @GetMapping
  @ApiOperation("Lấy ra danh sách slide")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<List<SlideResponseDto>> getAllSlides() {
    return new ResponseEntity<>(slideService.getAllSlide(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ApiOperation("Lấy ra slide dựa vào ID")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<SlideResponseDto> getSlide(@PathVariable(value = "id") UUID id) {
    return new ResponseEntity<>(slideService.getSlide(id), HttpStatus.OK);
  }

  @PostMapping
  @ApiOperation("Thêm slide")
  @ApiResponses({ @ApiResponse(code = 201, message = "") })
  public ResponseEntity<SlideResponseDto> createSlide(@Valid @RequestBody SlideRequestDto slideRequestDto) {
    return new ResponseEntity<>(slideService.create(slideRequestDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ApiOperation("Cập nhật Slide")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<SlideResponseDto> updateSlide(@PathVariable(value = "id") UUID id,
      @Valid @RequestBody SlideRequestDto slideRequestDto) {
    return new ResponseEntity<>(slideService.update(id, slideRequestDto), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  @ApiOperation("Cập nhật Slide")
  @ApiResponses({ @ApiResponse(code = 200, message = "") })
  public ResponseEntity<SlideResponseDto> updateSlideStatus(@PathVariable(value = "id") UUID id,
      @Valid @RequestBody UpdateStatusSlideRequestDto updateStatusSlideRequestDto) {
    return new ResponseEntity<>(slideService.updateStatus(id, updateStatusSlideRequestDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Xoá slide")
  @ApiResponses({ @ApiResponse(code = 204, message = "") })
  public ResponseEntity<?> deleteSlide(@PathVariable(value = "id") UUID id) {
    slideService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
