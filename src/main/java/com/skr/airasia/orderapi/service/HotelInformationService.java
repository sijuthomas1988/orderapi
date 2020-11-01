package com.skr.airasia.orderapi.service;

import com.skr.airasia.orderapi.dto.HotelDto;

/**
 * Hotel Information Service
 */
public interface HotelInformationService {

    /**
     * get Hotel Information Object from inventory table
     *
     * @param hotelRoomId
     *     room id of the hotel which booking is created for
     * @param hotelId
     *     hotel id for which booking is created for
     * @return Hotel details object from inventory table
     */
    HotelDto findByHotelAndRoomId(String hotelRoomId, String hotelId);

    /**
     * Saves hotel information to inventory table
     *
     * @param hotelDto
     *     Hotel details object to be saved to inventory table
     */
    void save(HotelDto hotelDto);
}
