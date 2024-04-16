package it.academy.servlets.extractors;

import it.academy.utils.interfaces.EntitySupplier;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

@UtilityClass
@Slf4j
public class FormExtractor {


    public static <T, R> String extract(HttpServletRequest req, Consumer<T> methodForSave,
                                        EntitySupplier<R> methodForGet, Class<T> resultClass,
                                        String objectKey, String exceptionPagePath, Supplier<String> successPagePath)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        log.info(String.format(CURRENT_CLASS,  FormExtractor.class.getSimpleName()));

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        Constructor<T> constructor = resultClass.getConstructor();
        T dto = Extractor.extract(req, constructor.newInstance());
        Field idField;
        Object id = null;
        try {
            idField = resultClass.getDeclaredField(OBJECT_ID);
            idField.setAccessible(true);
            id = idField.get(dto);
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), dto));
        }

        try {
            methodForSave.accept(dto);
            log.info(String.format(CURRENT_METHOD, methodForSave));
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), dto));

            R findResult = null;
            if (id != null) {
                findResult = methodForGet.get(id);
                log.info(String.format(CURRENT_METHOD, methodForGet));
            }
            req.setAttribute(ERROR, e.getMessage());
            String page = req.getParameter(PAGE);
            req.setAttribute(PAGE, page);
            req.setAttribute(objectKey, findResult == null ? dto : findResult);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return exceptionPagePath;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        log.info(String.format(FORM_EXTRACTED_PATTERN, dto));
        return successPagePath.get();
    }

}
