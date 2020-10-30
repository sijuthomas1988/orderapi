package com.skr.airasia.orderapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HOTEL_BOOKING_ATTEMPTS")
@Data
public class HotelBookingDto implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name="hotel_name")
    private String hotelName;

    @Column(name = "checkin_date")
    private Date hotelCheckInDate;

    @Column(name = "checkout_date")
    private Date hotelCheckOutDate;

    @Column(name = "room_id")
    private String hotelRoomId;

    @Column(name = "number_of_guests")
    private Long noOfGuests;

    @Column(name = "number_of_rooms_booked")
    private Long NoOfRooms;

    @Column(name = "room_type")
    private String roomType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerDto customerDto;
}
