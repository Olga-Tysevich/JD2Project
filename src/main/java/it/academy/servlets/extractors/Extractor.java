package it.academy.servlets.extractors;

import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.utils.ReflectionHelper;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.INVALID_NUMBER_FORMAT_ERROR;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@UtilityClass
@Slf4j
public class Extractor {

    public <T> T extractObject(HttpServletRequest request, T object) {
        T result = ReflectionHelper.setFieldValues(request, object);
        log.info(OBJECT_EXTRACTED_PATTERN, result);
        return result;
    }

    public TablePageReq extractDataForTable(HttpServletRequest request, String filterPage) {
        String command = extractString(request, COMMAND, StringUtils.EMPTY);
        int pageNumber = extractIntVal(request, PAGE_NUMBER, FIRST_PAGE);
        String page = extractString(request, PAGE, StringUtils.EMPTY);
        TablePageReq reqData =  TablePageReq.builder()
                .command(command)
                .pageNumber(pageNumber)
                .page(page)
                .filterPage(filterPage)
                .build();
        extractParameterMap(request, reqData);
        return reqData;
    }

    public void extractParameterMap(HttpServletRequest request, TablePageReq reqData) {
        List<String> parameterNames = FilterManager.getFiltersForPage(reqData.getPage());
        Map<String, String> userInput = parameterNames.stream()
                .filter(filter -> StringUtils.isNotBlank(request.getParameter(filter)))
                .collect(Collectors.toMap(
                        filter -> filter,
                        request::getParameter
                ));
        reqData.setUserInput(userInput);
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

    public RepairStatus extractRepairStatus(HttpServletRequest request) {
        try {
            return RepairStatus.valueOf(request.getParameter(REPAIR_STATUS));
        } catch (Exception e) {
            return null;
        }
    }

    public String extractMainPagePath(HttpServletRequest request) {
        RoleEnum role = (RoleEnum) request.getSession().getAttribute(ROLE);
        return RoleEnum.ADMIN.equals(role) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
    }

    public String extractLastPage(HttpServletRequest request) {
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE);
        return StringUtils.isBlank(lastPage) ? extractMainPagePath(request) : SLASH + lastPage;
    }

    public SparePartDTO extractSparePart(HttpServletRequest request) {
        SparePartDTO result = extractObject(request, new SparePartDTO());
        String[] modelsIdArray = request.getParameterValues(SPARE_PART_MODEL_ID);
        log.info(OBJECT_EXTRACTED_PATTERN, result);
        if (modelsIdArray != null) {
            List<Long> modelsId = List.of(modelsIdArray).stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            result.setModelIdList(modelsId);
        }
        return result;
    }

}
