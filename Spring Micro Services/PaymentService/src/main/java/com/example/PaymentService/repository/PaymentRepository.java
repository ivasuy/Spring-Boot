package com.example.PaymentService.repository;

import com.example.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<TransactionDetails, Long> {
    TransactionDetails findByOrderId(Long orderId);
}
