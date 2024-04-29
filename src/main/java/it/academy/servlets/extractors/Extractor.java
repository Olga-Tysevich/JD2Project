package it.academy.servlets.extractors;

import it.academy.dto.TableReq;
import it.academy.utils.ReflectionHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.INVALID_NUMBER_FORMAT_ERROR;

@UtilityClass
@Slf4j
public class Extractor {

    public <T> T extract(HttpServletRequest request, T object) {

        int pageNumber = request.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        String page = request.getParameter(PAGE);
        T result = ReflectionHelper.setFieldValues(request, object);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(PAGE, page);

        return result;
    }

    public TableReq extractDataForTable(HttpServletRequest request) {
        String command = extractString(request, COMMAND, DEFAULT_VALUE);
        int pageNumber = extractIntVal(request, PAGE_NUMBER, FIRST_PAGE);
        String page = extractString(request, PAGE, DEFAULT_VALUE);
        String userInput = extractString(request, USER_INPUT, DEFAULT_VALUE);
        String filter = extractString(request, FILTER, DEFAULT_VALUE);

        return TableReq.builder()
                .command(command)
                .pageNumber(pageNumber)
                .page(page)
                .input(userInput)
                .filter(filter)
                .build();
    }

    public String extractString(HttpServletRequest request, String parameterName, String defaultValue) {
        String result = request.getParameter(parameterName);
        return result != null? result : defaultValue;
    }

    public Integer extractIntVal(HttpServletRequest request, String parameterName, Integer defaultValue) {
        String parameterVal = request.getParameter(parameterName);
        if (parameterVal !=  null) {
            try {
                return Integer.parseInt(parameterVal);
            } catch (NumberFormatException e) {
                log.error(INVALID_NUMBER_FORMAT_ERROR, parameterVal);
            }
        }
        return defaultValue;
    }
}
