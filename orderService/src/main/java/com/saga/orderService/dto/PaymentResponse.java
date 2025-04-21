package com.saga.orderService.dto;

public class PaymentResponse {
    private String status;

    public PaymentResponse(String status) {
        this.status = status;
    }

    public PaymentResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}