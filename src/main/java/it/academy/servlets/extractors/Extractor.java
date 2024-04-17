package it.academy.servlets.extractors;

import it.academy.dto.resp.AccountDTO;
import it.academy.utils.ReflectionHelper;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
@Slf4j
public class Extractor {

    public static <T> T extract(HttpServletRequest request, T result) {

        log.info(String.format(CURRENT_CLASS,  FormExtractor.class.getSimpleName()));
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

        log.info(String.format(OBJECT_EXTRACTED_PATTERN, result));
        return result;
    }
}
