package it.academy.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ListForPage<T> {

    private int pageNumber;

    private int maxPageNumber;

    private List<T> list;

    private List<String> filtersForPage;

}
