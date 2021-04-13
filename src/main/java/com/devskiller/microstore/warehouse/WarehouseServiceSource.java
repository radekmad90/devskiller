package com.devskiller.microstore.warehouse;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface WarehouseServiceSource {
    @Output("warehouseQueue")
    MessageChannel sendPackage();
}
