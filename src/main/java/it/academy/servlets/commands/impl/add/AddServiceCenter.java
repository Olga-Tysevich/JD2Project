package it.academy.servlets.commands.impl.add;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.ServiceCenterExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private Extractor<ServiceCenterDTO> extractor = new ServiceCenterExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.extractValues(req);

        ServiceCenterDTO repairWorkshop = extractor.getResult();
        repairWorkshop.setIsActive(true);
        serviceCenterService.addServiceCenter(repairWorkshop);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
