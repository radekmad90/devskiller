package com.devskiller.microstore.payment;

import com.devskiller.microstore.stock.StockService;
import com.devskiller.microstore.warehouse.WarehouseService;

class PaymentService {

	private final StockService stockService;
	private final WarehouseService warehouseService;

	PaymentService(StockService stockService, WarehouseService warehouseService) {
		this.stockService = stockService;
		this.warehouseService = warehouseService;
	}

	void processOrderOnPayment(String orderId, PaymentRequest paymentRequest) {
		if (paymentRequest.isPaid() && stockService.checkAvailability(orderId)) {
			warehouseService.sendPackage(orderId);
		}
	}
}
