package com.devskiller.microstore.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

	private MessageChannel serviceSource;

	@Autowired
	public WarehouseService(@Qualifier("warehouseQueue") MessageChannel serviceSource) {
		this.serviceSource = serviceSource;
	}

	public void sendPackage(String orderId) {
		WarehousePayload payload = new WarehousePayload();
		payload.setAction("SEND");
		payload.setOrderId(orderId);
		Message<WarehousePayload> message = MessageBuilder.withPayload(payload).setHeader("contentType", "application/json").build();
		serviceSource.send(message);
	}
}
