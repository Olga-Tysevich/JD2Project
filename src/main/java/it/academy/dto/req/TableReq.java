package it.academy.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableReq {

    private int pageNumber;

    private String page;

    private String filter;

    private String input;

    private String command;

}
