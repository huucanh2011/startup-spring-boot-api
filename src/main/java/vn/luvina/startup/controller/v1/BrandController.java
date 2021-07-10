package vn.luvina.startup.controller.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.mbrand.BrandResponseDto;
import vn.luvina.startup.service.BrandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandController {

  private final BrandService brandService;

  @GetMapping
  @ApiOperation("Lấy danh sách brand")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  public ResponseEntity<List<BrandResponseDto>> getBrands(){
    return new ResponseEntity<>(brandService.getAll(),HttpStatus.OK);
  }
}
