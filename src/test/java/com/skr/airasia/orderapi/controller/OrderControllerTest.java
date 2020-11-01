package com.skr.airasia.orderapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skr.airasia.orderapi.dto.HotelDto;
import com.skr.airasia.orderapi.model.CustomerDetails;
import com.skr.airasia.orderapi.model.ErrorDetails;
import com.skr.airasia.orderapi.model.HotelDetails;
import com.skr.airasia.orderapi.model.OrderRequest;
import com.skr.airasia.orderapi.model.OrderResponse;
import com.skr.airasia.orderapi.model.RoomType;
import com.skr.airasia.orderapi.model.TransactionStatus;
import com.skr.airasia.orderapi.repository.HotelInformationRepository;
import com.skr.airasia.orderapi.service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OrderService orderService;

    @MockBean
    private HotelInformationRepository hotelInformationRepository;



    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void saveBookingDetails_success() throws Exception {
        HotelDto hotelDto = Mockito.mock(HotelDto.class);
        Mockito.when(hotelInformationRepository.findByHotelAndRoomId(any(), any())).thenReturn(hotelDto);
        Mockito.when(hotelDto.getNoOfRoomsAvailable()).thenReturn(5L);
        Mockito.when(hotelDto.getVersion()).thenReturn(15L);
        Mockito.when(hotelDto.getTableName()).thenReturn("HOTEL_DETAILS");
        Mockito.when(hotelDto.getId()).thenReturn(1L);
        Mockito.when(hotelInformationRepository.save(any())).thenReturn(hotelDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String exampleOrderInfo = objectMapper.writeValueAsString(createOrderRequestObject());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/createOrder")
                .accept(MediaType.APPLICATION_JSON).content(exampleOrderInfo)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        OrderResponse orderResponse = objectMapper.readValue(response.getContentAsString(), OrderResponse.class);
        Assert.assertEquals("200 http status returned", 201, status);
        Assert.assertNotNull(response);
        Assert.assertEquals(orderResponse.getTransactionStatus(), TransactionStatus.CONFIRMED);
    }

    @Test
    public void saveBookingDetails_failure() throws Exception {
        HotelDto hotelDto = Mockito.mock(HotelDto.class);
        Mockito.when(hotelInformationRepository.findByHotelAndRoomId(any(), any())).thenReturn(hotelDto);
        Mockito.when(hotelDto.getNoOfRoomsAvailable()).thenReturn(0L);

        ObjectMapper objectMapper = new ObjectMapper();
        String exampleOrderInfo = objectMapper.writeValueAsString(createOrderRequestObject());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/createOrder")
                .accept(MediaType.APPLICATION_JSON).content(exampleOrderInfo)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        ErrorDetails errorDetails = objectMapper.readValue(response.getContentAsString(), ErrorDetails.class);
        Assert.assertEquals(304, status);
        Assert.assertNotNull(response);
        Assert.assertEquals(errorDetails.getTransactionStatus(), TransactionStatus.REJECTED);
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