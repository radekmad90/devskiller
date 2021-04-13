package com.devskiller.microstore.stock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.devskiller.micro:stock:+:stubs")
public class StockClientTest {

    @Autowired
    StockClient stockClient;

    @Test
    public void stockRestClientIsImplemented() {
        StockResponse stockResponse = stockClient.checkStock("order1");

        assertThat(stockResponse.getOrderId()).isEqualToIgnoringCase("order1");
        assertThat(stockResponse.getStatus()).isEqualToIgnoringCase("AVAILABLE");
    }
}
