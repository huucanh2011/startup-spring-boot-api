package vn.luvina.startup.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.OrderStatus.OrderStatusSearchResponseDto;
import vn.luvina.startup.service.OrderStatusService;

@RestController
@RequestMapping("/api/v1/order-status")
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderStatusService orService;

    @GetMapping
    public ResponseEntity<OrderStatusSearchResponseDto> getAllStatus() {
        return new ResponseEntity<>(orService.getAllOrderStatus(), HttpStatus.OK);
    }
}
