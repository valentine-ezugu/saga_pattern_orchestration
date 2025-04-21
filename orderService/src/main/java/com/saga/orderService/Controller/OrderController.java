package com.saga.orderService.Controller;


import com.saga.orderService.model.Order;
import com.saga.orderService.model.SagaStep;
import com.saga.orderService.repository.SagaStepRepository;
import com.saga.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SagaStepRepository sagaStepRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @GetMapping("/{id}/saga-steps")
    public List<SagaStep> getSagaSteps(@PathVariable Long id) {
        return sagaStepRepository.findAll().stream()
                .filter(step -> step.getOrderId().equals(id))
                .collect(Collectors.toList());
    }
}


