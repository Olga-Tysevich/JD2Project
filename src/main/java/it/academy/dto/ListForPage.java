package it.academy.dto;

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

    private int pageNumber;

    private int maxPageNumber;

    private List<T> list;

    private List<EntityFilter> filtersForPage;

}
