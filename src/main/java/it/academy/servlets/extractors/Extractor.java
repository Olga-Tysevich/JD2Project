package it.academy.servlets.extractors;

import javax.servlet.http.HttpServletRequest;

public interface Extractor<T> {

    void extractValues(HttpServletRequest req);

    void insertAttributes(HttpServletRequest req);

    void addParameter(String parameterName, Object parameter);

    Object getParameter(String parameterName);

    T getResult();

}
