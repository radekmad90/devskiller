package com.devskiller.microstore.warehouse;

public class WarehousePayload {
    private String orderId;
    private String action;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
