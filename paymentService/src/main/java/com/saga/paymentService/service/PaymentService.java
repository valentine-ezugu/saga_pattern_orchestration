package com.saga.paymentService.service;

import com.saga.paymentService.dto.PaymentRequest;
import com.saga.paymentService.dto.PaymentResponse;
import com.saga.paymentService.model.Payment;
import com.saga.paymentService.model.PaymentStatus;
import com.saga.paymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SagaLogger sagaLogger;

    private final Random random = new Random();

    public PaymentResponse processPayment(PaymentRequest request) {
        boolean success = random.nextBoolean(); // Simulate random payment outcome
        PaymentStatus status = success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;

        Payment payment = new Payment(request.getOrderId(), request.getAmount(), status);
        paymentRepository.save(payment);

        sagaLogger.log(request.getOrderId(), "PAYMENT_PROCESSED", status.name());

        return new PaymentResponse(status.name());
    }

}
