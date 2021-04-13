package com.devskiller.microstore.stock;

import com.devskiller.microstore.StructureTestUtil;
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
class StockResponseStructureTest {

    private static final String STOCK_RESPONSE_CLASS_ENCODED_NAME = "Y29tLmRldnNraWxsZXIubWljcm9zdG9yZS5zdG9jay5TdG9ja1Jlc3BvbnNl";
    private static final String GET_STATUS_METHOD_ENCODED_NAME = "Z2V0U3RhdHVz";
    private static final String GET_ORDER_ID_METHOD_ENCODED_NAME = "Z2V0T3JkZXJJZA==";

    @Test
    @Order(1)
    @DisplayName("should be named StockResponse and be placed under com.devskiller.microstore.stock package")
    void stockResponseClass() {
        final Optional<Class<?>> stockResponseClass = tryToLoadClass(decode(STOCK_RESPONSE_CLASS_ENCODED_NAME)).toOptional();
        assertThat(stockResponseClass).isPresent();
        assertThat(stockResponseClass.get()).isNotFinal();
        assertThat(isPackagePrivate(stockResponseClass.get())).isTrue();
    }

    @Test
    @Order(2)
    @DisplayName("should have getStatus method without parameters")
    void getStatusMethod() {
        final Predicate<Method> getStatusPredicates = getterPredicates(GET_STATUS_METHOD_ENCODED_NAME).reduce(method -> true, Predicate::and);
        assertThat(isMethodPresent(StockResponse.class, getStatusPredicates)).isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("should have getOrderId method without parameters")
    void getOrderIdMethod() {
        final Predicate<Method> getOrderIdPredicates = getterPredicates(GET_ORDER_ID_METHOD_ENCODED_NAME).reduce(method -> true, Predicate::and);
        assertThat(isMethodPresent(StockResponse.class, getOrderIdPredicates)).isTrue();
    }

    private Stream<Predicate<Method>> getterPredicates(final String encodedMethodName) {
        return Stream.of(
                method -> hasEncodedName(method, encodedMethodName),
                StructureTestUtil::returnsString,
                StructureTestUtil::hasNoParameters,
                ReflectionUtils::isNotStatic,
                ReflectionUtils::isPublic,
                ReflectionUtils::isNotFinal
        );
    }
}
