package com.saga.paymentService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class SagaStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String step;
    private String status;
    private LocalDateTime timestamp;

    public SagaStep() {}

    public SagaStep(Long orderId, String step, String status) {
        this.orderId = orderId;
        this.step = step;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getOrderId() { return orderId; }
    public String getStep() { return step; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
