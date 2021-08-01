package vn.luvina.startup.dto.OrderStatus;

import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderStatusResponseDto {
    @ApiModelProperty(value = "id", example = "7c760a87-1841-4cd1-9357-98bbe0991e04")
    private UUID id;

    @ApiModelProperty(value = "name", example = "Lấy hàng thành công")
    private String name;

    @ApiModelProperty(value = "slug", example = "lay-hang-thanh-cong")
    private String slug;
}
