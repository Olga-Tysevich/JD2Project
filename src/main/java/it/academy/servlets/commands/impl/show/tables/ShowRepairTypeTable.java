package it.academy.servlets.commands.impl.show.tables;

import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowRepairTypeTable implements ActionCommand {
//    private Extractor<RepairTypeDTO> extractor = new RepairTypeExtractor();

    @Override
    public String execute(HttpServletRequest req) {

//        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
