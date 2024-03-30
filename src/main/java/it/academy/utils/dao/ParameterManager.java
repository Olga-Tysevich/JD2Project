package it.academy.utils.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static it.academy.utils.Constants.LIKE_QUERY_PATTERN;
import static it.academy.utils.Constants.PUNCTUATION_MARKS_PATTERN;

public class ParameterManager {

    private ParameterManager() {
    }

    public static List<ParameterContainer<?>> getQueryParameters(List<String> filters, String parameters) {
        List<String> parameterList = getParameters(parameters);
        List<ParameterContainer<?>> result = new ArrayList<>();

        filters.forEach(f -> parameterList.forEach(p -> {
                if (isIntNumber(p) || isDoubleNumber(p)) {
                    result.add(getNumber(f, p));
                } else {
                    result.add(new ParameterContainer<>(f, p));
                }
        }));
        return result;
    }

    private static List<String> getParameters(String parameters) {
        return Arrays.asList(parameters.split(PUNCTUATION_MARKS_PATTERN));
    }

    public static<T> String getParameterForLikeQuery(T parameter) {
        return String.format(LIKE_QUERY_PATTERN, parameter);
    }


    private static ParameterContainer<? extends Number> getNumber(String filter, Object parameter) {
        if (isIntNumber(parameter.toString())) {
            return new ParameterContainer<>(filter, Integer.parseInt(parameter.toString()));
        } else if (isDoubleNumber(parameter.toString())) {
            return new ParameterContainer<>(filter, Double.parseDouble(parameter.toString()));
        }
        return null;
    }


    private static boolean isIntNumber(String parameter) {
        try {
            Integer.parseInt(parameter);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDoubleNumber(String parameter) {
        try {
            Double.parseDouble(parameter);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
