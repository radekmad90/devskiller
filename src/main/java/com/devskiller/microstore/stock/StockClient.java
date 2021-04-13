package com.devskiller.microstore.stock;

interface StockClient {

	StockResponse checkStock(String orderId);
}
