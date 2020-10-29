package com.skr.airasia.orderapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Order request Model")
public class OrderRequest {

    @ApiModelProperty(notes = "Total amount of the transaction")
    private Long amount;

    @ApiModelProperty(notes = "Details about customer")
    private CustomerDetails customerDetails;

    @ApiModelProperty(notes = "Details about hotel booking")
    private HotelDetails hotelDetails;
}
