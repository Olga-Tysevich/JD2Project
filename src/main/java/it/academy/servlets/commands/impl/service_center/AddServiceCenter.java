package it.academy.servlets.commands.impl.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Extractor;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ServiceCenterDTO serviceCenterDTO = Extractor.extract(req, new ServiceCenterDTO());
        serviceCenterDTO.setIsActive(true);
        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        System.out.println("add create service " + serviceCenterDTO);

        try {
            serviceCenterService.addServiceCenter(serviceCenterDTO);
            System.out.println("add service created!");
        } catch (Exception e) {
            System.out.println(String.format(ERROR_PATTERN, e.getMessage(), serviceCenterDTO));
            req.setAttribute(SERVICE_CENTER, serviceCenterDTO);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return SERVICE_CENTER_PAGE_PATH;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MAIN_PAGE_PATH;
    }

}
