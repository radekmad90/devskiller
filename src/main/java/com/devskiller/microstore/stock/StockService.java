package com.devskiller.microstore.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	private final StockClient stockClient;

	@Autowired
	public StockService(StockClient stockClient) {
		this.stockClient = stockClient;
	}

	public boolean checkAvailability(String orderId) {
		StockResponse stockResponse = stockClient.checkStock(orderId);
		return "AVAILABLE".equals(stockResponse.getStatus());
	}
}
