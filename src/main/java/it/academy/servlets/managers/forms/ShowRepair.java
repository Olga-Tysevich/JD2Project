package it.academy.servlets.managers.forms;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairFormExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private Extractor extractor = new RepairFormExtractor();
    
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        return REPAIR_PAGE_PATH;

    }
    
}
