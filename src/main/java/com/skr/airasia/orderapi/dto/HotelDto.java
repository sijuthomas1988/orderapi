package com.skr.airasia.orderapi.dto;

import com.skr.airasia.orderapi.optimisticlocking.VersionedEntity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HOTEL_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto  implements Serializable, VersionedEntity {

    private final String TABLE = "HOTEL_DETAILS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hotel_id")
    private String hotel_id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_room_id")
    private String hotelRoomId;

    @Column(name = "hotel_room_type")
    private String hotelRoomType;

    @Column(name = "hotel_room_name")
    private String hotelRoomName;

    @Column(name = "hotel_no_available_rooms")
    private Long noOfRoomsAvailable;

    @Column(name = "version")
    private Long version;

    @Override
    public String getTableName() {
        return TABLE;
    }
}

