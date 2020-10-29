package com.skr.airasia.orderapi.service;

import com.skr.airasia.orderapi.exception.ServiceException;
import com.skr.airasia.orderapi.model.OrderRequest;
import com.skr.airasia.orderapi.model.OrderResponse;

public interface OrderService {

    OrderResponse insertOrder(OrderRequest orderRequest) throws ServiceException;
}
