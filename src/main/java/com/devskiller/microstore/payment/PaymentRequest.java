package com.devskiller.microstore.payment;

class PaymentRequest {

	private String status;

	boolean isPaid() {
		return "PAID".equals(status);
	}

	void setStatus(String status) {
		this.status = status;
	}
}
