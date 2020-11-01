package com.skr.airasia.orderapi.service.implementation;

import com.skr.airasia.orderapi.dto.HotelDto;
import com.skr.airasia.orderapi.dto.OrderDto;
import com.skr.airasia.orderapi.exception.ServiceException;
import com.skr.airasia.orderapi.model.CustomerDetails;
import com.skr.airasia.orderapi.model.HotelDetails;
import com.skr.airasia.orderapi.model.OrderRequest;
import com.skr.airasia.orderapi.model.OrderResponse;
import com.skr.airasia.orderapi.model.RoomType;
import com.skr.airasia.orderapi.model.TransactionStatus;
import com.skr.airasia.orderapi.repository.HotelInformationRepository;
import com.skr.airasia.orderapi.repository.OrderRepository;
import com.skr.airasia.orderapi.service.HotelInformationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.session.SessionRepositoryUnavailableException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private HotelInformationRepository hotelInformationRepository;

    @Mock
    private HotelInformationService hotelInformationService;

    @Test
    public void testInsertOrder_Success() throws ServiceException {
        HotelDto hotelDto = Mockito.mock(HotelDto.class);
        Mockito.when(hotelInformationService.findByHotelAndRoomId(any(), any())).thenReturn(hotelDto);
        Mockito.when(hotelDto.getNoOfRoomsAvailable()).thenReturn(5L);
        Mockito.when(hotelDto.getVersion()).thenReturn(15L);
        Mockito.when(hotelDto.getTableName()).thenReturn("HOTEL_DETAILS");
        Mockito.when(hotelDto.getId()).thenReturn(1L);
        Mockito.when(hotelInformationRepository.save(any())).thenReturn(hotelDto);
        Mockito.when(this.orderRepository.save(any())).thenReturn(Mockito.mock(OrderDto.class));
        OrderResponse orderResponse = this.orderServiceImpl.insertOrder(createOrderRequestObject());
        Assert.assertNotNull(orderResponse);
        Assert.assertEquals(orderResponse.getTransactionStatus(), TransactionStatus.CONFIRMED);
    }

    @Test(expected = ServiceException.class)
    public void testInsertOrder_Failure() throws ServiceException {
        HotelDto hotelDto = Mockito.mock(HotelDto.class);
        Mockito.when(hotelInformationService.findByHotelAndRoomId(any(), any())).thenReturn(hotelDto);
        Mockito.when(hotelDto.getNoOfRoomsAvailable()).thenReturn(0L);
        Mockito.when(hotelInformationRepository.save(any())).thenReturn(hotelDto);
        Mockito.when(this.orderRepository.save(any())).thenReturn(Mockito.mock(OrderDto.class));
        OrderResponse orderResponse = this.orderServiceImpl.insertOrder(createOrderRequestObject());
    }

    @Test(expected = ServiceException.class)
    public void testInsertOrder_Failure_ExceptionFromRepository() throws ServiceException {
        HotelDto hotelDto = Mockito.mock(HotelDto.class);
        Mockito.when(hotelInformationService.findByHotelAndRoomId(any(), any())).thenReturn(hotelDto);
        Mockito.when(hotelDto.getNoOfRoomsAvailable()).thenReturn(1L);
        Mockito.when(hotelInformationRepository.save(any())).thenReturn(hotelDto);
        Mockito.when(this.orderRepository.save(any())).thenThrow(SessionRepositoryUnavailableException.class);
        OrderResponse orderResponse = this.orderServiceImpl.insertOrder(createOrderRequestObject());
    }

    private OrderRequest createOrderRequestObject() {
        OrderRequest orderRequest = new OrderRequest();
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerEmail("testemail@email.com");
        customerDetails.setCustomerId(UUID.randomUUID().toString());
        customerDetails.setCustomerName("testname");
        customerDetails.setCustomerNumber("123456789");
        HotelDetails hotelDetails = new HotelDetails();
        hotelDetails.setHotelCheckInDate("10-11-2020");
        hotelDetails.setHotelCheckOutDate("11-11-2020");
        hotelDetails.setHotelId("testhotel_123");
        hotelDetails.setHotelName("testhotel_123");
        hotelDetails.setHotelNumberOfGuests("2");
        hotelDetails.setNoOfRooms(Long.valueOf(1));
        hotelDetails.setHotelRoomId("testhotel_room_1");
        hotelDetails.setHotelRoomName("testhotel_room_1");
        hotelDetails.setHotelRoomType(RoomType.STANDARD);
        orderRequest.setAmount(Long.valueOf(100));
        orderRequest.setCustomerDetails(customerDetails);
        orderRequest.setHotelDetails(hotelDetails);
        return orderRequest;
    }

}