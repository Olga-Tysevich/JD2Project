package it.academy.servlets.commands.impl.forms;

import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.SparePartOrderExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class AddSparePartOrder extends ShowOrderSparePart {
    private Extractor extractor = new SparePartOrderExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);


        return null;
    }

}
