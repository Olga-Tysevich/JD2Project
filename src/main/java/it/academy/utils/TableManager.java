package it.academy.utils;

import it.academy.dto.ListForPage;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.MAX_PAGE;

@UtilityClass
public class TableManager {

    public static<T> void insertAttributesForTable(HttpServletRequest req, ListForPage<T> list, String pagePath) {
        List<EntityFilter> filters = list.getFiltersForPage();

        req.setAttribute(PAGE, pagePath);
        req.setAttribute(FILTERS, filters);
        req.setAttribute(LIST_FOR_PAGE, list);
        req.setAttribute(PAGE_NUMBER, list.getPageNumber());
        req.setAttribute(MAX_PAGE, list.getMaxPageNumber());
    }
}
