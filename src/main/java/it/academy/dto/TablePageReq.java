package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TablePageReq {

    private int pageNumber;

    private String page;

    private Map<String, String> userInput;

    private String filterPage;

    private String command;

}
