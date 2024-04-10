package it.academy.utils.converters;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@UtilityClass
public class Converter {

    public static <T, R> R convertToDTO(T entity, Class<T> entityClass, Class<R> dtoClass) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        Constructor<R> constructor = dtoClass.getConstructor();
        constructor.setAccessible(true);
        R result = constructor.newInstance();

        List<Field> entityFields = List.of(entityClass.getDeclaredFields());
        List<Field> dtoFields = List.of(dtoClass.getDeclaredFields());
        entityFields.forEach(f -> f.setAccessible(true));


        dtoFields.forEach(f -> {
            f.setAccessible(true);
            String dtoFieldName = f.getName();

            Field entityField = dtoFields.stream()
                    .filter(ef -> ef.getName().equals(dtoFieldName))
                    .findFirst()
                    .orElse(null);
            if (entityField != null) {
                try {
                    Object val = f.get(result);
                    Object val2 = entityField.get(entity);
//                    f.set(result, val);
//                    f.set(result, f.getDeclaringClass().cast(val));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }

    public static <T, R> T convertToEntity(R dto) {
        return null;
    }

    public static <T, R> List<R> convertToDTO(List<T> entityList) {
        return null;
    }

    public static <T, R>  List<T> convertToEntity(List<R> dtoList) {
        return null;
    }


}
