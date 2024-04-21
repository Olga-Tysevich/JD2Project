package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.req.ServiceCenterDTO;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.ADD_SERVICE_CENTER;
import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_SERVICE_CENTER;
import static it.academy.utils.constants.Constants.*;

public class ShowServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        String tablePage = req.getParameter(PAGE);
        ServiceCenterDTO serviceCenterDTO;
        String id = req.getParameter(OBJECT_ID);

        if (id != null && !id.isBlank()) {
            long serviceCenterId = Long.parseLong(req.getParameter(OBJECT_ID));
            serviceCenterDTO = serviceCenterService.findServiceCenter(serviceCenterId);
            req.setAttribute(COMMAND, CHANGE_SERVICE_CENTER);
        } else  {
            serviceCenterDTO = Builder.buildEmptyServiceCenter();
            req.setAttribute(COMMAND, ADD_SERVICE_CENTER);
        }

        req.setAttribute(SERVICE_CENTER, serviceCenterDTO);
        req.setAttribute(PAGE, tablePage);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SERVICE_CENTER_PAGE_PATH;
    }

}
