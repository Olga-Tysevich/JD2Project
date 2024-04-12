package it.academy.utils;

import it.academy.entities.account.RoleEnum;
import lombok.experimental.UtilityClass;

import static it.academy.utils.Constants.UNSUPPORTED_CLASS;

@UtilityClass
public class ReflectionHelper {

    public static Object defineParameterType(String parameter, Class<?> fieldClass) {
        if (parameter == null) {
            return null;
        } else if (fieldClass == Integer.class || fieldClass == int.class) {
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
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_CLASS);
        }
    }
}
