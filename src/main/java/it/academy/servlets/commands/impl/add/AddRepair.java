package it.academy.servlets.commands.impl.add;

import it.academy.dto.repair.RepairDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private Extractor extractor = new RepairExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);
        RepairDTO repairDTO = ((RepairExtractor) extractor).getRepairDTO();
        repairService.addRepair(repairDTO);

        return MAIN_PAGE_PATH;
    }

}
