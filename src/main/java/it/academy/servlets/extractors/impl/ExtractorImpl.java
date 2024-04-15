package it.academy.servlets.extractors.impl;

import it.academy.dto.resp.AccountDTO;
import it.academy.utils.ReflectionHelper;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.experimental.UtilityClass;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import static it.academy.utils.Constants.ACCOUNT;
import static it.academy.utils.Constants.CURRENT_ACCOUNT;

@UtilityClass
public class ExtractorImpl {

    public static <T> T extract(HttpServletRequest request, T result) {
        Class<?> tClass = result.getClass();
        List<Field> fieldList = List.of(tClass.getDeclaredFields());
        fieldList.forEach(f -> {
            f.setAccessible(true);
            if (f.getName().equals(CURRENT_ACCOUNT)) {
                AccountDTO accountDTO = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
                ThrowingConsumerWrapper.apply(() -> f.set(result, accountDTO));
            } else {
                String value = request.getParameter(f.getName());
                if (value != null) {
                    ThrowingConsumerWrapper.apply(() -> f.set(result,
                            ReflectionHelper.defineParameterType(value, f.getType())));
                }
            }
        });

        return result;
    }
}
