package it.academy.utils;

import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;

import static it.academy.utils.constants.LoggerConstants.*;

@UtilityClass
@Slf4j
public class ReflectionHelper {

    public static <T> T setFieldValues(HttpServletRequest request, T result) {

        Class<?> tClass = result.getClass();
        List<Field> fieldList = List.of(tClass.getDeclaredFields());
        for (Field f : fieldList) {
            f.setAccessible(true);
            String value = request.getParameter(f.getName());
            if (value != null) {
                try {
                    f.set(result, ReflectionHelper.defineParameterType(value, f.getType()));
                } catch (IllegalArgumentException e) {
                    log.error(EXTRACT_ERROR_PATTERN, e.getMessage(), result, f.getName());
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Object defineParameterType(String parameter, Class<?> fieldClass) throws IllegalArgumentException {
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
            log.error(UNSUPPORTED_CLASS, fieldClass);
            throw new IllegalArgumentException(UNSUPPORTED_CLASS);
        }
    }
}
