package vn.luvina.startup.service;

import java.util.UUID;

import vn.luvina.startup.dto.OrderHistories.OrderHistoriesSearchResponseDto;

public interface OrderHistoriesService {
    public OrderHistoriesSearchResponseDto getOrderHistoriesUser(UUID userId, Integer pageNumber);
}
