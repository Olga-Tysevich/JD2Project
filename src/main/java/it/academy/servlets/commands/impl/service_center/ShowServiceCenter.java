package it.academy.servlets.commands.impl.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.service_center.ServiceCenterService;
import it.academy.services.service_center.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ServiceCenterDTO serviceCenterDTO;
        String id = req.getParameter(OBJECT_ID);
        System.out.println("service center id " + id);

        if (id != null && !id.isBlank()) {
            long repairWorkshopId = Long.parseLong(req.getParameter(OBJECT_ID));
            serviceCenterDTO = serviceCenterService.findServiceCenter(repairWorkshopId);
            System.out.println("service c " + serviceCenterDTO);

            System.out.println("command" + req.getParameter(COMMAND));
            req.setAttribute(COMMAND, CHANGE_SERVICE_CENTER);
        } else  {
            serviceCenterDTO = Builder.buildEmptyServiceCenter();
            System.out.println("service c " + serviceCenterDTO);
            req.setAttribute(COMMAND, ADD_SERVICE_CENTER);
        }

        req.setAttribute(SERVICE_CENTER, serviceCenterDTO);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SERVICE_CENTER_PAGE_PATH;
    }

}
