package it.academy.servlets.commands.impl.tables;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairTypeExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepairTypeTable implements ActionCommand {
    private Extractor<RepairTypeDTO> extractor = new RepairTypeExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
