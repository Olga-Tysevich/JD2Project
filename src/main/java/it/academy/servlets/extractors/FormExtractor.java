package it.academy.servlets.extractors;

import it.academy.dto.req.TableReq;
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
public class FormExtractor{


    public static <T, R> String extract(HttpServletRequest req, Consumer<T> methodForSave,
                                        EntitySupplier<R> methodForGet, Class<T> resultClass,
                                        String objectKey, String exceptionPagePath, Supplier<String> successPagePath)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{

        System.out.println(req.getParameter(IS_ACTIVE));

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        Constructor<T> constructor = resultClass.getConstructor();
        System.out.println("constructor " + constructor);
        T dto = ExtractorImpl.extract(req, constructor.newInstance());
        System.out.println("dto " + dto);
        System.out.println(dto);
        Field idField;
        Object id = null;
        try {
            idField = resultClass.getDeclaredField(OBJECT_ID);
            System.out.println(idField);
            idField.setAccessible(true);
            id = idField.get(dto);
        } catch (Exception ignored) {};

        System.out.println("object id " + id);
        TableReq request = ExtractorImpl.extract(req, new TableReq());
        System.out.println("change req " + request);
        System.out.println("change obj " + id);

        try {
            methodForSave.accept(dto);
            System.out.println("updated successful");
        } catch (Exception e) {
            System.out.println(String.format(ERROR_PATTERN, e.getMessage(), dto));

            R findResult = null;
            if (id != null) {
                findResult = methodForGet.get(id);
            }
            req.setAttribute(ERROR, e.getMessage());
            String page = req.getParameter(PAGE);
            req.setAttribute(PAGE, page);
            System.out.println("page " + page);
            req.setAttribute(objectKey, findResult == null ? dto : findResult);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            System.out.println("exception page " + exceptionPagePath);
            return exceptionPagePath;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return successPagePath.get();
    }

}
