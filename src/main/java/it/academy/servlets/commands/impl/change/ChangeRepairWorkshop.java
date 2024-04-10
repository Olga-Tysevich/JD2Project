package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.RepairWorkshopServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairWorkshopExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class ChangeRepairWorkshop implements ActionCommand {
    private RepairWorkshopService repairWorkshopService = new RepairWorkshopServiceImpl();
    private Extractor<RepairWorkshopDTO> extractor = new RepairWorkshopExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);
        RepairWorkshopDTO repairWorkshopDTO = extractor.getResult();
        repairWorkshopService.updateRepairWorkshop(repairWorkshopDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}