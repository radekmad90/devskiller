package com.devskiller.microstore.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.devskiller.microstore.StructureTestUtil.decode;
import static com.devskiller.microstore.StructureTestUtil.isPackagePrivate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

class PaymentServiceStructureTest {

    private static final String PAYMENT_SERVICE_CLASS_ENCODED_NAME = "Y29tLmRldnNraWxsZXIubWljcm9zdG9yZS5wYXltZW50LlBheW1lbnRTZXJ2aWNl";

    @Test
    @DisplayName("should be named PaymentService and be placed under com.devskiller.microstore.payment package")
    void paymentServiceClass() {
        final Optional<Class<?>> paymentServiceClass = tryToLoadClass(decode(PAYMENT_SERVICE_CLASS_ENCODED_NAME)).toOptional();
        assertThat(paymentServiceClass).isPresent();
        assertThat(paymentServiceClass.get()).isNotFinal();
        assertThat(isPackagePrivate(paymentServiceClass.get())).isTrue();
    }
}
