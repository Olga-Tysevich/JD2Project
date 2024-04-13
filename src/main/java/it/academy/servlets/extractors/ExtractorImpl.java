package it.academy.servlets.extractors;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.utils.ReflectionHelper;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

import static it.academy.utils.Constants.ACCOUNT;
import static it.academy.utils.Constants.CURRENT_ACCOUNT;

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
                ThrowingConsumerWrapper.apply(() -> f.set(result,
                        ReflectionHelper.defineParameterType(request.getParameter(f.getName()), f.getType())));
            }
        });

        return result;
    }
}
