package it.academy.servlets.managers.lists;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.ModelListExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.Constants.*;

public class ShowModelList implements ActionCommand {
    private Extractor extractor = new ModelListExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        return MODEL_LIST_PAGE_PATH;
    }
}
