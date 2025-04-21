package com.saga.paymentService.repository;

import com.saga.paymentService.model.SagaStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaStepRepository extends JpaRepository<SagaStep, Long> {}