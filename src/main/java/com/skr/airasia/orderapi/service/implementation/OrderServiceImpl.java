package com.skr.airasia.orderapi.service.implementation;

import com.skr.airasia.orderapi.dto.HotelDto;
import com.skr.airasia.orderapi.dto.OrderDto;
import com.skr.airasia.orderapi.exception.ServiceException;
import com.skr.airasia.orderapi.mapper.OrderMapper;
import com.skr.airasia.orderapi.model.TransactionStatus;
import com.skr.airasia.orderapi.model.OrderErrorCode;
import com.skr.airasia.orderapi.model.OrderRequest;
import com.skr.airasia.orderapi.model.OrderResponse;
import com.skr.airasia.orderapi.repository.HotelInformationRepository;
import com.skr.airasia.orderapi.repository.OrderRepository;
import com.skr.airasia.orderapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HotelInformationRepository hotelInformationRepository;

    @Override
    public OrderResponse insertOrder(OrderRequest orderRequest) throws ServiceException {
        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();
        String transactionId = UUID.randomUUID().toString();
        OrderDto orderDto = OrderMapper.convertRequestToDto(orderRequest, transactionId);
        try {
            HotelDto hotelDto = hotelInformationRepository
                    .findByHotelAndRoomId(orderRequest.getHotelDetails().getHotelRoomId(), orderRequest.getHotelDetails().getHotelId());
            if(hotelDto.getNoOfRoomsAvailable() > 0) {
                orderRepository.save(orderDto);
                orderResponse.transactionStatus(TransactionStatus.CONFIRMED);
                orderResponse.orderId(transactionId);
            } else {
                throw new ServiceException(OrderErrorCode.UNABLE_TO_PROCESS_ORDER.getCode(),
                        TransactionStatus.REJECTED.name(), OrderErrorCode.UNABLE_TO_PROCESS_ORDER.getMessage());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(OrderErrorCode.REPOSITORY_ERROR.getCode(), TransactionStatus.REJECTED.name(), e.getMessage());
        }
        return orderResponse.build();
    }
}
