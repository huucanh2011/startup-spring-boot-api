package vn.luvina.startup.controller.v1;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.OrderHistories.OrderHistoriesSearchResponseDto;
import vn.luvina.startup.service.OrderHistoriesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-order-histories")
public class OrderHistoriesController {

    private final OrderHistoriesService orHistoriesService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderHistoriesSearchResponseDto> getAllHistoriesOrderUser(@PathVariable(value = "id") UUID id,
            @RequestParam(name = "p", required = false) Integer pageNumber) {
        return new ResponseEntity<>(orHistoriesService.getOrderHistoriesUser(id, pageNumber), HttpStatus.OK);
    }
}
