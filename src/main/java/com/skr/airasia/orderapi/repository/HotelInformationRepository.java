package com.skr.airasia.orderapi.repository;

import com.skr.airasia.orderapi.dto.HotelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Hotel Details inventory crud
 */
public interface HotelInformationRepository extends JpaRepository<HotelDto, Long> {

    @Query(value = "SELECT * FROM HOTEL_DETAILS t WHERE t.hotel_id = :hotelId AND t.hotel_room_id = :roomId", nativeQuery = true)
    HotelDto findByHotelAndRoomId(@Param("roomId") String roomId, @Param("hotelId") String HotelId);
}
