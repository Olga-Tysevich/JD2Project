package it.academy.servlets.commands.impl.tables;

import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairWorkshopExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepairWorkshopTable implements ActionCommand {
    private Extractor<RepairWorkshopDTO> extractor = new RepairWorkshopExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.insertAttributes(req);
        req.setAttribute(SHOW_COMMAND, SHOW_REPAIR_WORKSHOP_TABLE);

        return MAIN_PAGE_PATH;
    }
}