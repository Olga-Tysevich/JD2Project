package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_TABLE_PAGE_PATH;

public class ShowServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ServiceCenterDTO serviceCenterDTO;
        String id = req.getParameter(OBJECT_ID);
        CommandEnum command;

        if (id != null && !id.isBlank()) {
            long serviceCenterId = Long.parseLong(req.getParameter(OBJECT_ID));
            serviceCenterDTO = serviceCenterService.findServiceCenter(serviceCenterId);
            command = CHANGE_SERVICE_CENTER;
        } else  {
            serviceCenterDTO = Builder.buildEmptyServiceCenter();
            command = ADD_SERVICE_CENTER;
        }

        req.setAttribute(SERVICE_CENTER, serviceCenterDTO);

        return CommandHelper.insertFormData(req,
                SERVICE_CENTER_TABLE_PAGE_PATH,
                SERVICE_CENTER_PAGE_PATH,
                command,
                SHOW_SERVICE_CENTER_TABLE);
    }

}
