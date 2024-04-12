package it.academy.dto.table.resp;

import it.academy.utils.EntityFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListForPage<T> {

    private Integer pageNumber;

    private Integer maxPageNumber;

    private List<T> list;

    private List<EntityFilter> filtersForPage;

    private String command;

    private String page;

}
