package com.skr.airasia.orderapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "HotelDetails Model")
public class HotelDetails {
    @ApiModelProperty(notes = "hotel id")
    private String hotelId;

    @ApiModelProperty(notes = "hotel name")
    private String hotelName;

    @ApiModelProperty(notes = "hotel checkin date")
    private String hotelCheckInDate;

    @ApiModelProperty(notes = "hotel checkout date")
    private String hotelCheckOutDate;

    @ApiModelProperty(notes = "hotel room id")
    private String hotelRoomId;

    @ApiModelProperty(notes = "hotel room name")
    private String hotelRoomName;

    @ApiModelProperty(notes = "hotel number of guests")
    private String hotelNumberOfGuests;

    @ApiModelProperty(notes = "No of Rooms Booked")
    private Long noOfRooms;

    @ApiModelProperty(notes = "hotel room type")
    private RoomType hotelRoomType;
}
