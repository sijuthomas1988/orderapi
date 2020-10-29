package com.skr.airasia.orderapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Customer Model")
public class CustomerDetails {

    @ApiModelProperty(notes = "customer name")
    private String customerName;

    @ApiModelProperty(notes = "customer email")
    private String customerEmail;

    @ApiModelProperty(notes = "customer number")
    private String customerNumber;

    @ApiModelProperty(notes = "customer_id")
    private String customerId;
}
