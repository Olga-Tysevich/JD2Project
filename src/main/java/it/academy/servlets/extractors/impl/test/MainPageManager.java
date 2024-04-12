package it.academy.servlets.extractors.impl.test;

import it.academy.dto.ListForPage;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.MAX_PAGE;

@UtilityClass
public class MainPageManager {

    public static<T> void insertAttributesForTable(HttpServletRequest req, ListForPage<T> list) {
        req.setAttribute(PAGE_NUMBER, list.getPageNumber());
        req.setAttribute(MAX_PAGE, list.getMaxPageNumber());
        req.setAttribute(LIST_FOR_PAGE, list);
        req.setAttribute(FILTERS, list.getFiltersForPage());

        System.out.println("ins page number " + list.getPageNumber());
        System.out.println("ins max page number " + list.getMaxPageNumber());
        System.out.println("ins list " + list);
        System.out.println("ins filters " + list.getFiltersForPage());
    }

}
