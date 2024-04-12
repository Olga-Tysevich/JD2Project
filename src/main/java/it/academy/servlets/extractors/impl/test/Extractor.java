package it.academy.servlets.extractors.impl.test;

import it.academy.entities.account.RoleEnum;
import it.academy.utils.helpers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

import static it.academy.utils.Constants.UNSUPPORTED_CLASS;

public class Extractor {

    public static <T> T extract(HttpServletRequest request, T result) {
        Class<?> tClass = result.getClass();
        List<Field> fieldList = List.of(tClass.getDeclaredFields());
        fieldList.forEach(f -> {
            f.setAccessible(true);
            ThrowingConsumerWrapper.apply(() -> f.set(result,
                    defineParameterType(request.getParameter(f.getName()), f.getType())));
        });

        return result;
    }

    private static Object defineParameterType(String parameter, Class<?> fieldClass) {
        if (fieldClass == Integer.class || fieldClass == int.class) {
            return Integer.parseInt(parameter);
        } else if (fieldClass == Double.class || fieldClass == double.class) {
            return Double.parseDouble(parameter);
        } else if (fieldClass == Long.class || fieldClass == long.class) {
            return Long.parseLong(parameter);
        } else if (fieldClass == RoleEnum.class) {
            return RoleEnum.valueOf(parameter);
        } else if (fieldClass == String.class) {
            return parameter;
        } else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
            return Boolean.valueOf(parameter);
        } else if (parameter == null) {
            return null;
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_CLASS);
        }
    }
}
