package com.devskiller.microstore.stock;

public class StockService {

	private final StockClient stockClient;

	public StockService(StockClient stockClient) {
		this.stockClient = stockClient;
	}

	public boolean checkAvailability(String orderId) {
		StockResponse stockResponse = stockClient.checkStock(orderId);
		return "AVAILABLE".equals(stockResponse.getStatus());
	}
}
