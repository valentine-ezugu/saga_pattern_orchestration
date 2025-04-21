package com.saga.orderService.repository;

import com.saga.orderService.model.SagaStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaStepRepository extends JpaRepository<SagaStep, Long> {}
