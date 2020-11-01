package com.skr.airasia.orderapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Order response model")
public class OrderResponse {

    @ApiModelProperty(notes = "booking status")
    private TransactionStatus transactionStatus;

    @ApiModelProperty(notes = "order confirmation id")
    private String orderId;
}
