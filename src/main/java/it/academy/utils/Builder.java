package it.academy.utils;

import it.academy.dto.ListForPage;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Builder {

    public static<T> ListForPage<T> buildListForPage(List<T> list, int pageNumber, int maxPageNumber, List<String> filters) {
        return ListForPage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber(maxPageNumber)
                .filtersForPage(filters)
                .build();
    }

}
