package vn.luvina.startup.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.OrderStatus.OrderStatusResponseDto;
import vn.luvina.startup.dto.OrderStatus.OrderStatusSearchResponseDto;
import vn.luvina.startup.model.OrderStatus;
import vn.luvina.startup.repository.OrderStatusRepository;
import vn.luvina.startup.service.OrderStatusService;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderStatusSearchResponseDto getAllOrderStatus(){
        OrderStatusSearchResponseDto listResult = new OrderStatusSearchResponseDto();
        List<OrderStatus> listStatus = orderStatusRepository.findAll();
        List<OrderStatusResponseDto> listStatusDto = new ArrayList<>();
        for (int i = 0; i < listStatus.size(); i++) {
            OrderStatusResponseDto ordertStatusDto = modelMapper.map(listStatus.get(i), OrderStatusResponseDto.class);
            listStatusDto.add(ordertStatusDto);
        }
        listResult.setListOrderStatus(listStatusDto);
       return listResult; 
    }
}
