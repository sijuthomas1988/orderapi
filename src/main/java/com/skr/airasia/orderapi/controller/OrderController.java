package com.skr.airasia.orderapi.controller;

import com.skr.airasia.orderapi.exception.ServiceException;
import com.skr.airasia.orderapi.model.ErrorDetails;
import com.skr.airasia.orderapi.model.OrderRequest;
import com.skr.airasia.orderapi.model.OrderResponse;
import com.skr.airasia.orderapi.service.OrderService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class
 */
@RestController
@RequestMapping("/v1")
@Api(value = "Order API")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Saves an object of order details of a user in the database",
            notes = "Saves an object of order details of a user in the database", tags = {})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully saved the booking object"),
            @ApiResponse(code = 304, message = "Resource was not updated", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class),
            @ApiResponse(code = 304, message = "Resource you are trying to reach is not found/available", response = ErrorDetails.class)})
    @RequestMapping(value = "/createOrder", produces = {"application/json"}, method = RequestMethod.POST)
    @Timed
    public ResponseEntity<OrderResponse> saveBookingDetails(@RequestBody OrderRequest orderRequest) throws ServiceException {
        OrderResponse orderResponse = this.orderService.insertOrder(orderRequest);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
    }
}
