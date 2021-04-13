package com.devskiller.microstore.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "stock")
public interface StockClientImpl extends StockClient {
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/products/{orderId}", produces = "application/json")
    StockResponse checkStock(@PathVariable("orderId") String orderId);
}
