package com.skr.airasia.orderapi.service;

import com.skr.airasia.orderapi.exception.ServiceException;
import com.skr.airasia.orderapi.model.OrderRequest;
import com.skr.airasia.orderapi.model.OrderResponse;

/**
 * Order Service
 */
public interface OrderService {

    /**
     * Insert Order of a user to order table
     * @param orderRequest
     *     order request object
     * @return order response object
     * @throws ServiceException
     *     exception thrown
     */
    OrderResponse insertOrder(OrderRequest orderRequest) throws ServiceException;
}
