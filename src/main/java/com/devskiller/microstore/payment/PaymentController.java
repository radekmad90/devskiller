package com.devskiller.microstore.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payments", consumes = "application/json")
class PaymentController {

	private final PaymentService paymentService;

	@Autowired
	PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PutMapping("/{orderId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	void receivePayment(@PathVariable("orderId") String orderId, @RequestBody PaymentRequest paymentRequest) {
		paymentService.processOrderOnPayment(orderId, paymentRequest);
	}
}
