package it.academy.servlets.extractors;

import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.utils.ReflectionHelper;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.INVALID_NUMBER_FORMAT_ERROR;

@UtilityClass
@Slf4j
public class Extractor {

    public <T> T extract(HttpServletRequest request, T object) {

//        int pageNumber = request.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
//        String page = request.getParameter(PAGE);
        T result = ReflectionHelper.setFieldValues(request, object);
//        request.setAttribute(PAGE_NUMBER, pageNumber);
//        request.setAttribute(PAGE, page);

        return result;
    }

    public TablePageReq extractDataForTable(HttpServletRequest request) {
        String command = extractString(request, COMMAND, StringUtils.EMPTY);
        int pageNumber = extractIntVal(request, PAGE_NUMBER, FIRST_PAGE);
        String page = extractString(request, PAGE,  StringUtils.EMPTY);
        String userInput = extractString(request, USER_INPUT,  StringUtils.EMPTY);
        String filter = extractString(request, FILTER,  StringUtils.EMPTY);

        return TablePageReq.builder()
                .command(command)
                .pageNumber(pageNumber)
                .page(page)
                .input(userInput)
                .filter(filter)
                .build();
    }


    public String extractString(HttpServletRequest request, String parameterName, String defaultValue) {
        String result = request.getParameter(parameterName);
        return result != null ? result : defaultValue;
    }

    public Integer extractIntVal(HttpServletRequest request, String parameterName, Integer defaultValue) {
        String parameterVal = request.getParameter(parameterName);
        if (parameterVal != null) {
            try {
                return Integer.parseInt(parameterVal);
            } catch (NumberFormatException e) {
                log.error(INVALID_NUMBER_FORMAT_ERROR, parameterVal);
            }
        }
        return defaultValue;
    }

    public Long extractLongVal(HttpServletRequest request, String parameterName, Long defaultValue) {
        String parameterVal = request.getParameter(parameterName);
        if (parameterVal != null) {
            try {
                return Long.parseLong(parameterVal);
            } catch (NumberFormatException e) {
                log.error(INVALID_NUMBER_FORMAT_ERROR, parameterVal);
            }
        }
        return defaultValue;
    }

    public Date extractDate(HttpServletRequest request, String parameterName) {
        try {
            return Date.valueOf(request.getParameter(parameterName));
        } catch (Exception e) {
            return null;
        }
    }

    public RepairStatus extractRepairStatus(HttpServletRequest request) {
        try {
            return RepairStatus.valueOf(request.getParameter(REPAIR_STATUS));
        } catch (Exception e) {
            return null;
        }
    }

    public RepairCategory extractRepairCategory(HttpServletRequest request) {
        try {
            return RepairCategory.valueOf(request.getParameter(REPAIR_CATEGORY));
        } catch (Exception e) {
            return null;
        }
    }
}
