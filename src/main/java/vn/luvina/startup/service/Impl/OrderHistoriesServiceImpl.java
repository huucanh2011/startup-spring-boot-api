package vn.luvina.startup.service.Impl;

import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.OrderHistories.OrderHistoriesResponseDto;
import vn.luvina.startup.dto.OrderHistories.OrderHistoriesSearchResponseDto;
import vn.luvina.startup.mapper.MetaMapper;
import vn.luvina.startup.model.OrderHistory;
import vn.luvina.startup.repository.OrderHistoriesRepository;
import vn.luvina.startup.service.OrderHistoriesService;
import vn.luvina.startup.util.Constants;

@Service
@RequiredArgsConstructor
public class OrderHistoriesServiceImpl implements OrderHistoriesService {

    private final OrderHistoriesRepository orHisRepository;

    private final ModelMapper modelMapper;

    private final MetaMapper metaMapper;

    @Override
    public OrderHistoriesSearchResponseDto getOrderHistoriesUser(UUID userId, Integer pageNumber) {
        int limit = Constants.LIMIT_DEFAULT;
        Page<OrderHistory> searchResult;
        OrderHistoriesSearchResponseDto listOrderHistories = new OrderHistoriesSearchResponseDto();

        // Gán các giá trị mặc định và giá trị cho phân trang.
        if (pageNumber == null) {
            pageNumber = Constants.PAGE_DEFAULT;
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, limit);
        searchResult = orHisRepository.findAllByUserId(userId, pageable);
        listOrderHistories.setData(searchResult.getContent().stream()
                .map(orderHis -> modelMapper.map(orderHis, OrderHistoriesResponseDto.class))
                .collect(Collectors.toList()));
        listOrderHistories.setMeta(metaMapper.convertToMetaResponse(pageNumber, limit, searchResult));
        return listOrderHistories;
    }
}
