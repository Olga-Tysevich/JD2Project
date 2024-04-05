package it.academy.servlets.extractors;

import javax.servlet.http.HttpServletRequest;

public interface Extractor {

    void extractValues(HttpServletRequest req);
}
