package it.academy.servlets.extractors.impl;

import javax.servlet.http.HttpServletRequest;

public class ExtractorImpl<T, R>  {
    private R service;
    private T dto;


    public void extractValues(HttpServletRequest req) {

    }


    public void insertAttributes(HttpServletRequest req) {

    }


    public void addParameter(String parameterName, Object parameter) {

    }


    public Object getParameter(String parameterName) {
        return null;
    }


    public T getResult() {
        return null;
    }

}
