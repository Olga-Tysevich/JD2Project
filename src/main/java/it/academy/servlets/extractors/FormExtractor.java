package it.academy.servlets.extractors;

import it.academy.dto.table.req.TableReq;
import it.academy.utils.Extractor;
import it.academy.utils.interfaces.EntitySupplier;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

@UtilityClass
public class FormExtractor {

    public static <T, R> String extract(HttpServletRequest req, Consumer<T> methodForSave,
                                        EntitySupplier<R> methodForGet, Class<T> objectClass,
                                        String objectKey, String exceptionPagePath, Supplier<String> successPagePath)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        System.out.println(req.getParameter(IS_ACTIVE));

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        Constructor<T> constructor = objectClass.getConstructor();
        System.out.println("constructor " + constructor);
        T dto = Extractor.extract(req, constructor.newInstance());
        System.out.println("dto " + dto);
        System.out.println(dto);
        Field idField = objectClass.getDeclaredField(OBJECT_ID);
        System.out.println(idField);
        idField.setAccessible(true);
        Long id = (Long) idField.get(dto);
        System.out.println("object id " + id);
        TableReq request = Extractor.extract(req, new TableReq());
        System.out.println("change req " + request);
        System.out.println("change obj " + id);

        try {
            methodForSave.accept(dto);
            System.out.println("updated successful");
        } catch (Exception e) {
            System.out.println(String.format(ERROR_PATTERN, e.getMessage(), dto));

            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(PAGE, req.getParameter(PAGE));
            req.setAttribute(objectKey, methodForGet.get(id));
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return exceptionPagePath;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return successPagePath.get();
    }
}
