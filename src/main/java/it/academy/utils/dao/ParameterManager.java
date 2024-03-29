package it.academy.utils.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.academy.utils.Constants.LIKE_QUERY_PATTERN;
import static it.academy.utils.Constants.PUNCTUATION_MARKS_PATTERN;

public class ParameterManager {

    private ParameterManager() {
    }

    public static List<ParameterContainer> getQueryParameters(List<String> filters, String parameters) {
        List<String> parameterList = getParameters(parameters);
        List<ParameterContainer> result = new ArrayList<>();
        filters.forEach(f -> {
            parameterList.forEach(p -> result.add(new ParameterContainer(f, getParameter(p), true)));
        });
        return result;
    }

    private static List<String> getParameters(String parameters) {
        return Arrays.asList(parameters.split(PUNCTUATION_MARKS_PATTERN));
    }

    private static String getParameter(String parameter) {
        return String.format(LIKE_QUERY_PATTERN, parameter);
    }
}
