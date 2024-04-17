package it.academy.utils;

import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;

import java.sql.Date;

import static it.academy.utils.constants.Constants.UNSUPPORTED_CLASS;

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
        } else if (fieldClass == RepairStatus.class) {
            return RepairStatus.valueOf(parameter);
        } else if (fieldClass == RepairCategory.class) {
            return RepairCategory.valueOf(parameter);
        } else if (fieldClass == String.class) {
            return parameter;
        } else if (fieldClass == Date.class) {
            if (!parameter.isEmpty()) {
                return Date.valueOf(parameter);
            }
            return null;
        } else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
            return Boolean.valueOf(parameter);
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_CLASS);
        }
    }
}
