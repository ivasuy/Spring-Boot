package com.vasu.OrderService.external.client;

import com.vasu.OrderService.external.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/api/payments")
public interface PaymentService {
    @PostMapping
   ResponseEntity<Long> doPayment(
           @RequestBody PaymentRequest paymentRequest
    );
}
