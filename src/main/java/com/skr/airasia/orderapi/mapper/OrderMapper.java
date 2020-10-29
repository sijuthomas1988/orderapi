package com.skr.airasia.orderapi.mapper;

import com.skr.airasia.orderapi.dto.HotelBookingDto;
import com.skr.airasia.orderapi.dto.OrderDto;
import com.skr.airasia.orderapi.dto.CustomerDto;
import com.skr.airasia.orderapi.model.OrderRequest;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Class that enables to map from request to dto object
 */
public class OrderMapper {

    /**
     * Converts request to dto object
     *
     * @param orderRequest
     *     request object
     * @param transactionId
     *     unique id generated value
     * @return dto object
     *
     */
    public static OrderDto convertRequestToDto(OrderRequest orderRequest, String transactionId ) {
        OrderDto orderDto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        HotelBookingDto hotelBookingDto = new HotelBookingDto();
        hotelBookingDto.setHotelCheckInDate(parseDateFromString(orderRequest.getHotelDetails().getHotelCheckInDate()));
        hotelBookingDto.setHotelCheckOutDate(parseDateFromString(orderRequest.getHotelDetails().getHotelCheckOutDate()));
        hotelBookingDto.setCustomerDto(customerDto);
        hotelBookingDto.setHotelId(orderRequest.getHotelDetails().getHotelId());
        hotelBookingDto.setHotelName(orderRequest.getHotelDetails().getHotelName());
        hotelBookingDto.setHotelRoomId(orderRequest.getHotelDetails().getHotelRoomId());
        hotelBookingDto.setNoOfGuests(Long.parseLong(orderRequest.getHotelDetails().getHotelNumberOfGuests()));
        hotelBookingDto.setRoomType(orderRequest.getHotelDetails().getHotelRoomType().name());
        customerDto.setHotelBookingDetails(Arrays.asList(hotelBookingDto));
        customerDto.setEmail(orderRequest.getCustomerDetails().getCustomerEmail());
        customerDto.setName(orderRequest.getCustomerDetails().getCustomerName());
        customerDto.setCustomer_id(orderRequest.getCustomerDetails().getCustomerId());
        customerDto.setPhoneNumber(orderRequest.getCustomerDetails().getCustomerNumber());
        customerDto.setOrderDetails(orderDto);
        orderDto.setCustomerDto(customerDto);
        orderDto.setTotalAmount(orderRequest.getAmount());
        orderDto.setTransactionId(transactionId);
        return orderDto;
    }

    /**
     * Function to parse date from string
     *
     * @param dateString
     *     date string value
     * @return date
     */
    private static Date parseDateFromString(String dateString) {
        long date=new SimpleDateFormat("dd-MM-yyyy").parse(dateString, new ParsePosition(0)).getTime();
        return new java.sql.Date(date);
    }
}