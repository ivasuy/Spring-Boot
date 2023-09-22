package com.vasu.OrderService.controller;

import com.vasu.OrderService.model.OrderRequest;
import com.vasu.OrderService.model.OrderResponse;
import com.vasu.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id : {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable ("id") Long orderId){

        OrderResponse orderResponse = orderService.getOrderDetails(orderId);

        return new ResponseEntity<>(orderResponse,HttpStatus.FOUND);
    }

}
