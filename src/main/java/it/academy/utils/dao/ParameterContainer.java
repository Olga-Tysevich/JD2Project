package it.academy.utils.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterContainer<T> {
    private String parameterName;
    private T parameterValue;
    private Boolean isEqualsQuery;
}
