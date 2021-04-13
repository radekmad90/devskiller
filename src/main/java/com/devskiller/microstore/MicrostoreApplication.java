package com.devskiller.microstore;

import com.devskiller.microstore.warehouse.WarehouseServiceSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableFeignClients
@EnableBinding(WarehouseServiceSource.class)
public class MicrostoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrostoreApplication.class, args);
	}
}
