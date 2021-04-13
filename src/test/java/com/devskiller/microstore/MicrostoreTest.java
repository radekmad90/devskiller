package com.devskiller.microstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.devskiller.micro:stock:+:stubs")
public class MicrostoreTest {

	@Autowired
	WebApplicationContext webAppContextSetup;

	@Autowired
	ContractVerifierMessaging contractVerifierMessaging;

	@Autowired
	ContractVerifierObjectMapper contractVerifierObjectMapper;

	@Before
	public void setUp() {
		RestAssuredMockMvc.webAppContextSetup(webAppContextSetup);
	}

	@Test
	public void shouldProcessOrderPayment() throws JsonProcessingException {
		// given:
		MockMvcRequestSpecification request = given()
				.header("Content-Type", "application/json")
				.body("{\"status\":\"PAID\"}");

		// when:
		ResponseOptions response = given().spec(request).put("/payments/order1");

		// then:
		assertThat(response.statusCode()).isEqualTo(202);

		// and:
		ContractVerifierMessage warehouseMessage = contractVerifierMessaging.receive("warehouseQueue");
		assertThat(warehouseMessage).isNotNull();
		assertThat(warehouseMessage.getHeader("contentType")).isNotNull();
		assertThat(warehouseMessage.getHeader("contentType").toString()).isEqualTo("application/json");

		DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(warehouseMessage.getPayload()));
		assertThatJson(parsedJson).field("['orderId']").isEqualTo("order1");
		assertThatJson(parsedJson).field("['action']").isEqualTo("SEND");
	}


}