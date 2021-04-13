package com.devskiller.microstore.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Constructor;
import java.util.Optional;

import static com.devskiller.microstore.StructureTestUtil.decode;
import static com.devskiller.microstore.StructureTestUtil.isPackagePrivate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

@TestMethodOrder(OrderAnnotation.class)
class PaymentControllerStructureTest {

    private static final String PAYMENT_CONTROLLER_CLASS_ENCODED_NAME = "Y29tLmRldnNraWxsZXIubWljcm9zdG9yZS5wYXltZW50LlBheW1lbnRDb250cm9sbGVy";

    @Test
    @Order(1)
    @DisplayName("should be named PaymentController and be placed under com.devskiller.microstore.payment package")
    void paymentControllerClass() {
        final Optional<Class<?>> paymentControllerClass = tryToLoadClass(decode(PAYMENT_CONTROLLER_CLASS_ENCODED_NAME)).toOptional();
        assertThat(paymentControllerClass).isPresent();
        assertThat(paymentControllerClass.get()).isNotFinal();
        assertThat(isPackagePrivate(paymentControllerClass.get())).isTrue();
    }

    @Test
    @Order(2)
    @DisplayName("should have only one constructor with PaymentService parameter")
    void paymentControllerConstructor() {
        final Constructor<PaymentController> paymentControllerConstructor = getDeclaredConstructor(PaymentController.class);
        assertThat(isPackagePrivate(paymentControllerConstructor)).isTrue();
        assertThat(paymentControllerConstructor.getGenericParameterTypes()).containsExactly(PaymentService.class);
    }
}
