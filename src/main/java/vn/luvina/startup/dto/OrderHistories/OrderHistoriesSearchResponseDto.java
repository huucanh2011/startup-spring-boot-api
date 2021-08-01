package vn.luvina.startup.dto.OrderHistories;

import java.util.List;

import lombok.Data;
import vn.luvina.startup.dto.base.MetaResponse;

@Data
public class OrderHistoriesSearchResponseDto {
    private List<OrderHistoriesResponseDto> data;

    private MetaResponse meta;
}
