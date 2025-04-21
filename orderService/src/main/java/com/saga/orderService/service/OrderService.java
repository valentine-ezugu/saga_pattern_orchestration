package com.saga.orderService.service;

import com.saga.orderService.dto.PaymentRequest;
import com.saga.orderService.dto.PaymentResponse;
import com.saga.orderService.feign.PaymentClient;
import com.saga.orderService.model.Order;
import com.saga.orderService.model.OrderStatus;
import com.saga.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private SagaLogger sagaLogger;

    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.CREATED);
        order = orderRepository.save(order);
        sagaLogger.log(order.getId(), "ORDER_CREATED", "SUCCESS");

        try {
            PaymentResponse response = paymentClient.processPayment(
                    new PaymentRequest(order.getId(), order.getAmount())
            );

            sagaLogger.log(order.getId(), "PAYMENT_ATTEMPTED", response.getStatus());

            if ("SUCCESS".equals(response.getStatus())) {
                order.setStatus(OrderStatus.PAYMENT_SUCCESS);
            } else {
                cancelOrder(order.getId());
                sagaLogger.log(order.getId(), "ORDER_COMPENSATED", "SUCCESS");
            }
        } catch (Exception e) {
            sagaLogger.log(order.getId(), "PAYMENT_CALL_FAILED", "FAILED");
            cancelOrder(order.getId());
            sagaLogger.log(order.getId(), "ORDER_COMPENSATED", "SUCCESS");
        }

        return orderRepository.save(order);
    }

    public Order cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
