# Saga Pattern Orchestration - Spring Boot Microservices

## 📌 Project Goal
This project demonstrates the **Saga Orchestration pattern** using two Spring Boot microservices:

- **Order Service**: Initiates the saga by creating an order and orchestrating the flow.
- **Payment Service**: Processes payments and reports success or failure to the orchestrator.

The objective is to show how to manage distributed transactions without using distributed locks or global transactions — by using a Saga pattern with compensation logic.

---

## 🧩 Microservices Overview

### 🛒 Order Service
- Runs on: `localhost:8080`
- Responsibilities:
    - Create orders
    - Call the Payment Service
    - Handle payment result
    - Perform compensation (`cancelOrder`) if needed
    - Log each step in a `SagaStep` table for observability

### 💳 Payment Service
- Runs on: `localhost:8081`
- Responsibilities:
    - Receive payment requests from Order Service
    - Simulate success/failure
    - Respond with status
    - Log payment processing in its own `SagaStep` table

---

## 🔄 Saga Orchestration Logic

1. Order is created via `POST /orders` (Order Service)
2. Order Service saves the order with status `CREATED`
3. Order Service calls Payment Service (`POST /payments`) using **FeignClient**
4. Payment Service randomly returns `SUCCESS` or `FAILED`
5. Based on the response:
    - If `SUCCESS` → Order status becomes `PAYMENT_SUCCESS`
    - If `FAILED` → `cancelOrder()` is triggered (compensation)
6. Every step is logged via a `SagaLogger` into a `SagaStep` table

---

## 🐳 Docker Setup

To run both services using Docker:

### Step 1: Package JARs
```bash
./mvnw clean package -DskipTests
```

### Step 2: Build and Start Containers
```bash
docker-compose up --build
```

### Default Ports
- Order Service: `localhost:8080`
- Payment Service: `localhost:8081`

---

## 🔍 Accessing the Services

### Order Service
- `POST /orders` — Create a new order
- `PUT /orders/{id}/cancel` — Compensate an order
- `GET /orders/{id}/saga-steps` — View saga log for an order

### Payment Service
- `POST /payments` — Trigger a payment process

---

## 🧠 Key Concepts

### Saga Orchestration
- **Order Service acts as the orchestrator**
- **Payment Service is a participant**
- Compensation logic is built into the orchestrator
- Uses **direct method call** for internal compensation (`cancelOrder()`)
- Could also expose `/cancel` as a REST endpoint for external orchestrators

### Logging
- Each service maintains its own **SagaStep** log table
- Enables observability and debugging of distributed transactions

---

## ✅ Future Enhancements
- Add Shipping Service
- Centralize saga logs - because we need to be able to query a whole process 
  and see where it failed easily a central Saga log is needed
- Add retries and timeouts ( the bases for the retry func is SagaStep- that's why we store it in db)
- Dockerize with PostgreSQL for persistence

---

## 👨‍💻 Author
Learning real-world distributed transaction handling through Spring Boot microservices, 
Saga patterns, and hands-on Docker orchestration.

