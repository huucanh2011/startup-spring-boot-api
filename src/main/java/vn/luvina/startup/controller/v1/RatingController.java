package vn.luvina.startup.controller.v1;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.rating.RatingRequestDto;
import vn.luvina.startup.dto.rating.RatingResponseDto;
import vn.luvina.startup.dto.rating.RatingSearchRequestDto;
import vn.luvina.startup.dto.rating.RatingSearchResponseDto;
import vn.luvina.startup.service.RatingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ratings")
public class RatingController {
  private final RatingService ratingService;

  @GetMapping("/{page}")
  @ApiOperation("Lấy toàn bộ đánh giá")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  public ResponseEntity<RatingSearchResponseDto> getRatings(@RequestParam(required = false) Integer page, Integer limit ){
    RatingSearchRequestDto ratingSearchRequestDto = new RatingSearchRequestDto();
    ratingSearchRequestDto.setLimit(limit);
    ratingSearchRequestDto.setPage(page);
    return new ResponseEntity<>(ratingService.getAll(ratingSearchRequestDto), HttpStatus.OK);
  }

  @PostMapping("/product/{productId}")
  @ApiOperation("Tạo mới đánh giá")
  @ApiResponses({@ApiResponse(code = 201, message = "")})
  public ResponseEntity<RatingResponseDto> createRating(@PathVariable(value = "productId") UUID productId, @Valid @RequestBody RatingRequestDto ratingRequestDto){
    return new ResponseEntity<>(ratingService.create(productId, ratingRequestDto), HttpStatus.CREATED);
  }

  @PutMapping("/{ratingId}/active/{isActive}")
  @ApiOperation("Cập nhật trạng thái đánh giá")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  public ResponseEntity<RatingResponseDto> updateStateRating(@PathVariable(value = "ratingId") UUID id,
    @PathVariable(value = "isActive") boolean isActive){
    return new ResponseEntity<>(ratingService.updateState(id, isActive), HttpStatus.OK);
  }

  @PutMapping("/{ratingId}")
  @ApiOperation("Cập nhật đánh giá")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  public ResponseEntity<RatingResponseDto> updateRating(@PathVariable(value = "ratingId") UUID id,
  @Valid @RequestBody RatingRequestDto ratingRequestDto){
    return new ResponseEntity<>(ratingService.update(id, ratingRequestDto), HttpStatus.OK);
  }

  @DeleteMapping("/{ratingId}")
  @ApiOperation("Xoá đánh giá")
  @ApiResponses({@ApiResponse(code = 204, message = "")})
  public ResponseEntity<?> deleteRating(@PathVariable(value = "ratingId") UUID id){
    ratingService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/product-rating/{productId}")
  @ApiOperation("Đánh giá của sản phẩm")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  public ResponseEntity<RatingSearchResponseDto> getProductRating(@PathVariable(value = "productId") UUID productId,
    @RequestParam(required = false) Integer page, Integer limit){
    RatingSearchRequestDto ratingSearchRequestDto = new RatingSearchRequestDto();
    ratingSearchRequestDto.setLimit(limit);
    ratingSearchRequestDto.setPage(page);
    return new ResponseEntity<>(ratingService.getRatingByProduct(productId, ratingSearchRequestDto), HttpStatus.OK);
  }
}
