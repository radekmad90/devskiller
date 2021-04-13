package com.devskiller.microstore;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;

import static java.lang.reflect.Modifier.*;

public class StructureTestUtil {

    private StructureTestUtil() {
    }

    public static boolean isPackagePrivate(final Member member) {
        return isPackagePrivate(member.getModifiers());
    }

    public static <T> boolean isPackagePrivate(final Class<T> clazz) {
        return isPackagePrivate(clazz.getModifiers());
    }

    private static boolean isPackagePrivate(final int modifiers) {
        return !(isPublic(modifiers) || isPrivate(modifiers) || isProtected(modifiers));
    }

    public static boolean hasEncodedName(final Member member, final String encodedName) {
        final String decodedMethodName = decode(encodedName);
        return decodedMethodName.equals(member.getName());
    }

    public static boolean returnsString(final Method method) {
        return returns(method, String.class);
    }

    public static <T> boolean returns(final Method method, final Class<T> returnType) {
        return returnType.equals(method.getReturnType());
    }

    public static boolean hasParameters(final Method method, final Class<?>... parameters) {
        return Arrays.equals(method.getParameterTypes(), parameters);
    }

    public static boolean hasNoParameters(final Method method) {
        return method.getParameterCount() == 0;
    }

    public static String decode(final String encodedText) {
        return new String(Base64.getDecoder().decode(encodedText));
    }
}
