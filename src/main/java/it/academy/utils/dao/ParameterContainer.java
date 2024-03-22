package it.academy.utils.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterContainer {
    private Class<?> parameterType;
    private Object parameterValue;
}
