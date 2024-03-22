package it.academy.utils.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.academy.utils.Constants.LIKE_QUERY_PATTERN;
import static it.academy.utils.Constants.PUNCTUATION_MARKS_PATTERN;

public class ParameterManager {

    private ParameterManager() {
    }

    public static List<ParameterContainer> getQueryParameters(String parameters) {
        List<String> parameterList = getParameters(parameters);
        List<ParameterContainer> result = new ArrayList<>();
        parameterList.forEach(p -> {
            if (getNumber(p) == null) {
                result.add(new ParameterContainer(String.class, String.format(LIKE_QUERY_PATTERN,p)));
            } else {
                result.add(getNumber(p));
            }
        });
        return result;
    }

    private static List<String> getParameters(String parameters) {
        return Arrays.asList(parameters.split(PUNCTUATION_MARKS_PATTERN));
    }

    private static ParameterContainer getNumber(String parameter) {
        if (isIntNumber(parameter)) {
            return new ParameterContainer(Integer.class, Integer.parseInt(parameter));
        } else if (isDoubleNumber(parameter)) {
            return new ParameterContainer(Double.class, Double.parseDouble(parameter));
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
