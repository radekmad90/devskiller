package com.devskiller.microstore.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.devskiller.microstore.StructureTestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.commons.util.ReflectionUtils.isMethodPresent;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

@TestMethodOrder(OrderAnnotation.class)
class StockClientStructureTest {

    private static final String STOCK_CLIENT_INTERFACE_ENCODED_NAME = "Y29tLmRldnNraWxsZXIubWljcm9zdG9yZS5zdG9jay5TdG9ja0NsaWVudA==";
    private static final String CHECK_STOCK_METHOD_ENCODED_NAME = "Y2hlY2tTdG9jaw==";

    @Test
    @Order(1)
    @DisplayName("should be named StockClient and be placed under com.devskiller.microstore.stock package")
    void stockClientClass() {
        final Optional<Class<?>> stockClientInterface = tryToLoadClass(decode(STOCK_CLIENT_INTERFACE_ENCODED_NAME)).toOptional();
        assertThat(stockClientInterface).isPresent();
        assertThat(stockClientInterface.get()).isInterface();
        assertThat(isPackagePrivate(stockClientInterface.get())).isTrue();
    }

    @Test
    @Order(2)
    @DisplayName("should have checkStock method with String parameter")
    void checkStockMethod() {
        final Predicate<Method> checkStockPredicates = checkStockPredicates().reduce(method -> true, Predicate::and);
        assertThat(isMethodPresent(StockClient.class, checkStockPredicates)).isTrue();
    }

    private Stream<Predicate<Method>> checkStockPredicates() {
        return Stream.of(
                method -> hasEncodedName(method, CHECK_STOCK_METHOD_ENCODED_NAME),
                method -> returns(method, StockResponse.class),
                method -> hasParameters(method, String.class),
                ReflectionUtils::isNotStatic
        );
    }
}
