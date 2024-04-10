package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairWorkshopExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class ChangeRepairWorkshop implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private Extractor<RepairWorkshopDTO> extractor = new RepairWorkshopExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);
        RepairWorkshopDTO repairWorkshopDTO = extractor.getResult();
        serviceCenterService.updateRepairWorkshop(repairWorkshopDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}