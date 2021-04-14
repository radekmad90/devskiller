package com.devskiller.microstore.warehouse;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Warehouse {
    @Output("warehouseQueue")
    MessageChannel sendPackage();
}
