package it.academy.dto;

import it.academy.utils.fiterForSearch.EntityFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablePage<T> {

    private Integer pageNumber;

    private Integer maxPageNumber;

    private List<T> list;

    private List<EntityFilter> filtersForPage;

    private String command;

    private String page;

    private String lastFilter;

    private String lastInput;

}
