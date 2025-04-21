package com.saga.orderService.service;

import com.saga.orderService.model.SagaStep;
import com.saga.orderService.repository.SagaStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaLogger {

    @Autowired
    private SagaStepRepository repo;

    public void log(Long orderId, String step, String status) {
        repo.save(new SagaStep(orderId, step, status));
    }
}
