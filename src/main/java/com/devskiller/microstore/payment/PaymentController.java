package com.devskiller.microstore.payment;

class PaymentController {

	private final PaymentService paymentService;

	PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	void receivePayment(String orderId, PaymentRequest paymentRequest) {
		paymentService.processOrderOnPayment(orderId, paymentRequest);
	}
}
