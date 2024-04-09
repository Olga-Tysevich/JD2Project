package it.academy.servlets.commands.impl.tables;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.SparePartExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class ShowSparePartTable implements ActionCommand {
    private SparePartExtractor extractor = new SparePartExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
