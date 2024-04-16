package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.req.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_SERVICE_CENTER;
import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_SERVICE_CENTER;
import static it.academy.utils.Constants.*;

public class ShowServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ServiceCenterDTO serviceCenterDTO;
        String id = req.getParameter(OBJECT_ID);

        if (id != null && !id.isBlank()) {
            long repairWorkshopId = Long.parseLong(req.getParameter(OBJECT_ID));
            serviceCenterDTO = serviceCenterService.findServiceCenter(repairWorkshopId);

            req.setAttribute(COMMAND, CHANGE_SERVICE_CENTER);
        } else  {
            serviceCenterDTO = Builder.buildEmptyServiceCenter();
            req.setAttribute(COMMAND, ADD_SERVICE_CENTER);
        }

        req.setAttribute(SERVICE_CENTER, serviceCenterDTO);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SERVICE_CENTER_PAGE_PATH;
    }

}
