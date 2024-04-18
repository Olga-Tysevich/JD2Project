package it.academy.servlets.extractors;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
@Slf4j
public class TableExtractor {

    public static Map<String, Object> extract(HttpServletRequest request) {

        Map<String, Object> pageData = new HashMap<>();
        int pageNumber = request.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        String page = request.getParameter(PAGE) != null ? request.getParameter(PAGE) : MAIN_PAGE_PATH;
        String userInput = request.getParameter(USER_INPUT);
        String filter = request.getParameter(FILTER);
        String command = request.getParameter(COMMAND);

        pageData.put(PAGE_NUMBER, pageNumber);
        pageData.put(PAGE, page);
        pageData.put(USER_INPUT, userInput);
        pageData.put(FILTER, filter);
        pageData.put(COMMAND, command);

        return pageData;
    }

}
