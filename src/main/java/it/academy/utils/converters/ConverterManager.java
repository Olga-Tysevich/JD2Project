package it.academy.utils.converters;

import it.academy.utils.ReflectionHelper;
import it.academy.utils.helpers.ThrowingConsumerWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class ConverterManager {

//    public static <T, R> R convert(T object, Class<?> resultClass) {
//        Class<?> tClass = object.getClass();
//
//        Constructor<R> constructor =
//        R result = ;
//        List<Field> objectFieldList = List.of(tClass.getDeclaredFields());
//        List<Field> resultFieldList = List.of(resultClass.getDeclaredFields());
//
//        resultFieldList.forEach(f -> {
//            f.setAccessible(true);
//
//            ThrowingConsumerWrapper.apply(() -> f.set(result,
//                    ReflectionHelper.defineParameterType(request.getParameter(f.getName()), f.getType())));
//        });
//
//        return result;
//    }
}
