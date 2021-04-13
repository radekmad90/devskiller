package com.devskiller.microstore.warehouse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.devskiller.microstore.StructureTestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.commons.util.ReflectionUtils.isMethodPresent;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

@TestMethodOrder(OrderAnnotation.class)
class WarehouseServiceStructureTest {

    private static final String WAREHOUSE_SERVICE_CLASS_ENCODED_NAME = "Y29tLmRldnNraWxsZXIubWljcm9zdG9yZS53YXJlaG91c2UuV2FyZWhvdXNlU2VydmljZQ==";
    private static final String SEND_PACKAGE_METHOD_ENCODED_NAME = "c2VuZFBhY2thZ2U=";

    @Test
    @Order(1)
    @DisplayName("should be named WarehouseService and be placed under com.devskiller.microstore.warehouse package")
    void warehouseServiceClass() {
        final Optional<Class<?>> stockServiceClass = tryToLoadClass(decode(WAREHOUSE_SERVICE_CLASS_ENCODED_NAME)).toOptional();
        assertThat(stockServiceClass).isPresent();
        assertThat(stockServiceClass.get()).hasAnnotation(Service.class).isPublic().isNotFinal();
    }

    @Test
    @Order(2)
    @DisplayName("should have sendPackage method with String parameter")
    void sendPackageMethod() {
        final Predicate<Method> sendPackagePredicates = sendPackagePredicates().reduce(method -> true, Predicate::and);
        assertThat(isMethodPresent(WarehouseService.class, sendPackagePredicates)).isTrue();
    }

    private Stream<Predicate<Method>> sendPackagePredicates() {
        return Stream.of(
                method -> hasEncodedName(method, SEND_PACKAGE_METHOD_ENCODED_NAME),
                ReflectionUtils::returnsVoid,
                method -> hasParameters(method, String.class),
                ReflectionUtils::isPublic,
                ReflectionUtils::isNotStatic,
                ReflectionUtils::isNotFinal
        );
    }
}
