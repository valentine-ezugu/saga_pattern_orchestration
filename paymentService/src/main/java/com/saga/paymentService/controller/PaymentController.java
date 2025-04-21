package com.saga.paymentService.controller;

import com.saga.paymentService.dto.PaymentRequest;
import com.saga.paymentService.dto.PaymentResponse;
import com.saga.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping
    public PaymentResponse pay(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }
}
