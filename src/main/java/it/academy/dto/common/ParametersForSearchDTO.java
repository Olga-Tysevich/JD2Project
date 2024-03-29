package it.academy.dto.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParametersForSearchDTO {

    int pageNumber;

    int listSize;

    List<String> filters;

    String userInput;

}
