package vn.luvina.startup.dto.OrderStatus;

import java.util.List;

import lombok.Data;

@Data
public class OrderStatusSearchResponseDto {
    private List<OrderStatusResponseDto> listOrderStatus;
}
