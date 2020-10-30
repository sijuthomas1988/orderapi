package com.skr.airasia.orderapi.service;

import com.skr.airasia.orderapi.dto.HotelDto;

public interface HotelInformationService {

    HotelDto findByHotelAndRoomId(String hotelRoomId, String hotelId);

    void save(HotelDto hotelDto);
}
