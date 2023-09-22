package com.vasu.OrderService.service;

import com.vasu.OrderService.entity.Order;
import com.vasu.OrderService.exception.CustomException;
import com.vasu.OrderService.external.client.PaymentService;
import com.vasu.OrderService.external.client.ProductService;
import com.vasu.OrderService.external.request.PaymentRequest;
import com.vasu.OrderService.external.response.PaymentResponse;
import com.vasu.OrderService.external.response.ProductResponse;
import com.vasu.OrderService.model.OrderRequest;
import com.vasu.OrderService.model.OrderResponse;
import com.vasu.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;


@Log4j2
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        /*Order Entity -> Save the data with Status Order Created
        Product Service - Block Products (Reduce the Quantity)
        Payment Service -> Payments -> Success-> COMPLETE, Else CANCELLED
        Check if order in stock or not
        Also if in stock and we sell it we should reduce it's quantity (Product Service)
        And then do payment if payment is done successful else cancelled (Payment Service) */

        log.info("Placing Order Request : {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating Order with Status CREATED");

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .orderStatus("CREATED")
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        log.info("Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus = null;

        try{
            paymentService.doPayment(paymentRequest);
            log.info("Payment Done Successfully. Changing the Order Status to Placed");
            orderStatus = "PLACED";
        } catch (Exception e){
            log.error("Error occurred in payment. Changing order Status to Failed");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        order = orderRepository.save(order);

        log.info("Order Placed {}", order.getOrderId());
        return order.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        log.info("Get order details for Order Id: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found for the order Id:" + orderId, "ORDER_NOT_FOUND", 404));

        log.info("Invoking Product service to fetch the product for id: {}", order.getProductId());

        ProductResponse productResponse = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/api/products/" + order.getProductId(),
                ProductResponse.class
        );

        log.info("Getting Payment Information from the Payment Service");

        PaymentResponse paymentResponse = restTemplate.getForObject(
                "http://PAYMENT-SERVICE/api/payments/order/" + order.getOrderId(),
                PaymentResponse.class
        );

        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails
                .builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity())
                .build();

        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        return orderResponse;
    }
}
