package com.vasu.OrderService.service;

import com.vasu.OrderService.model.OrderRequest;
import com.vasu.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);
}
