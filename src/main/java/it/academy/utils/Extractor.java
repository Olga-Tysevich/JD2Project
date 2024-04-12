package it.academy.utils;

import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

public class Extractor {

    public static <T> T extract(HttpServletRequest request, T result) {
        Class<?> tClass = result.getClass();
        List<Field> fieldList = List.of(tClass.getDeclaredFields());
        fieldList.forEach(f -> {
            f.setAccessible(true);

            ThrowingConsumerWrapper.apply(() -> f.set(result,
                    ReflectionHelper.defineParameterType(request.getParameter(f.getName()), f.getType())));
        });

        return result;
    }
}
