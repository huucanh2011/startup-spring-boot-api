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
import vn.luvina.startup.dto.msize.SizeResponseDto;
import vn.luvina.startup.service.SizeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sizes")
public class SizeController {
private final SizeService sizeService;

  @GetMapping
  @ApiOperation("Lấy danh sách size")
  @ApiResponses({@ApiResponse(code = 200, message = "")})
  public ResponseEntity<List<SizeResponseDto>> getSizes(){
    return new ResponseEntity<>(sizeService.getAll(), HttpStatus.OK);
  }
}
