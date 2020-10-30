package com.skr.airasia.orderapi.service.implementation;

import com.skr.airasia.orderapi.dto.HotelDto;
import com.skr.airasia.orderapi.optimisticlocking.OptimisticlyLocked;
import com.skr.airasia.orderapi.repository.HotelInformationRepository;
import com.skr.airasia.orderapi.service.HotelInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HotelInformationServiceImpl implements HotelInformationService {

    @Autowired
    private HotelInformationRepository hotelInformationRepository;

    @Override
    public HotelDto findByHotelAndRoomId(String hotelRoomId, String hotelId) {
        HotelDto hotelDto;
        try {
            hotelDto = hotelInformationRepository.findByHotelAndRoomId(hotelRoomId, hotelId);
            return hotelDto;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    @OptimisticlyLocked
    public void save(HotelDto hotelDto) {
        try {
            hotelInformationRepository.saveAndFlush(hotelDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
