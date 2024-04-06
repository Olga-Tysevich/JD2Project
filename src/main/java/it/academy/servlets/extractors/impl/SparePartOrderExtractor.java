package it.academy.servlets.extractors.impl;

import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.ORDER_ID;

public class SparePartOrderExtractor implements Extractor {

    @Override
    public void extractValues(HttpServletRequest req) {

        long orderId = Long.parseLong(req.getParameter(ORDER_ID));

    }

}
