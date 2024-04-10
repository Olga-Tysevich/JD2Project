package it.academy.utils.converters;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@UtilityClass
public class Converter {

    public static Object convert(Object object, Class<?> objectClass, Class<?> resultClass) {

        Constructor<?> constructor = null;
        Object result = null;
        try {
            constructor = resultClass.getConstructor();
            constructor.setAccessible(true);
            Object temp = constructor.newInstance();
            List<Field> entityFields = List.of(objectClass.getDeclaredFields());
            List<Field> dtoFields = List.of(resultClass.getDeclaredFields());
            entityFields.forEach(f -> f.setAccessible(true));

            for (Field f : dtoFields) {
                f.setAccessible(true);
                String dtoFieldName = f.getName();

                Field entityField = entityFields.stream()
                        .filter(ef -> ef.getName().equals(dtoFieldName))
                        .findFirst()
                        .orElse(null);
                if (entityField != null) {
                    Object val = entityField.get(object);
                    f.set(temp, val);
                } else if (dtoFieldName.endsWith("DTO")) {
                    Class<?> clazz = f.getDeclaringClass();
                    Field entityObjectField = entityFields.stream()
                            .filter(ef -> ef.getName().equals(dtoFieldName.replace("DTO", "")))
                            .findFirst()
                            .orElse(null);
                    if (entityObjectField != null) {
                        Class<?> clazz2 = entityObjectField.getDeclaringClass();
                        f.set(temp, convert(entityObjectField, clazz2, clazz));
                    }
                }
            }
            result = temp;

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

}
