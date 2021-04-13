Microstore microservice
=

## Introduction
You took over the project from the vendor who had no experience with Spring Cloud framework. They've started the implementation however the project after few months is even not compiling correctly.

The good thing is that there is an correctly implemented test `com.devskiller.microstore.MicrostoreTest`, which verifies the whole process:
1. Receive REST `PUT` request to `/payments/{orderId}` URL.
2. Follow the business logic implemented in the `PaymentService` and `StockService` classes under the hood.
3. If everything is correct (order is paid and available on stock) the message is sent to the `warehouseQueue` queue.

## Problem Statement

Complete the instruction below:
1. Configure all `*Service` classes so that they are included in Spring context.
2. Implement the `PaymentController` class to support the following request:
    - `PUT /payments/{orderId}` 
    - Example request `{"status":"PAID"}`. 
    - It must return `202 Accepted` status.
3. Implement the `StockClient` class using `openfeign` library. It must send request to the application registered in service discovery as `stock`, with the following structure:
    - `GET /products/{orderId}`
    - `Accept: application/json`
    - Example response: `{"orderId":"order1","status":"AVAILABLE"}` (already defined in the `StockResponse` class)
4. Implement the `WarehouseService`. Use `spring-cloud-stream` and send the message with the following definition:
    - Queue name is `warehouseQueue`
    - `contentType` header of the message must be set to `application/json`
    - Message payload structure: `{"orderId":"order1", "action":"SEND"}`
 
 ## Hints
 
 You can always check the `com.devskiller.microstore.MicrostoreTest` class to check the source of the test.