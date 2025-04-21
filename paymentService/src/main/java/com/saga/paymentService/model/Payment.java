package com.saga.paymentService.model;

import jakarta.persistence.*;


@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment() {
    }

    public Payment(Long orderId, Double amount, PaymentStatus status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
