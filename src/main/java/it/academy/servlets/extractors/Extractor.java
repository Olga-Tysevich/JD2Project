package it.academy.servlets.extractors;

import javax.servlet.http.HttpServletRequest;

public interface Extractor {

    void extractValues(HttpServletRequest req);

    void insertAttributes(HttpServletRequest req);

    void addParameter(String parameterName, Object parameter);

    Object getParameter(String parameterName);

}
