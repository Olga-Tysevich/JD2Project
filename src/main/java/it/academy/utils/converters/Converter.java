package it.academy.utils.converters;

import it.academy.utils.ReflectionHelper;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class Converter {

    public static <T, R> R convert(T object, Class<R> resultClass) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> objectClass = object.getClass();
        Constructor<R> constructor = resultClass.getConstructor();
        R result = constructor.newInstance();

        List<Field> objectFieldList = List.of(objectClass.getDeclaredFields());
        objectFieldList.forEach(f -> f.setAccessible(true));
        Map<String, Field> fieldMap = objectFieldList.stream()
                .collect(Collectors.toMap(Field::getName, Function.identity()));
        List<Field> resultFieldList = List.of(resultClass.getDeclaredFields());

        resultFieldList.forEach(f -> {
            f.setAccessible(true);

            ThrowingConsumerWrapper.apply(() -> {
                Object parameter = fieldMap.get(f.getName()).get(object);
                if (parameter != null) {
                    f.set(result,
                            ReflectionHelper.defineParameterType(parameter.toString(), f.getType()));
                }
            });
        });

        return result;
    }
}
