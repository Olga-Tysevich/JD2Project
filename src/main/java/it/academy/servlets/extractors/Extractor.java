package it.academy.servlets.extractors;

import it.academy.utils.ReflectionHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
@Slf4j
public class Extractor {

    public <T> T extract(HttpServletRequest request, T object) {

        int pageNumber = request.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        String page = request.getParameter(PAGE) != null ? request.getParameter(PAGE) : MAIN_PAGE_PATH;
        T result = ReflectionHelper.setFieldValues(request, object);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(PAGE, page);

        return result;
    }
}
