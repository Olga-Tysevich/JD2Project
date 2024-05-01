package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TablePageReq {

    private int pageNumber;

    private String page;

    private String filter;

    private String input;

    private String command;

}
