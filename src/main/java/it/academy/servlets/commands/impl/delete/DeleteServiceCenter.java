package it.academy.servlets.commands.impl.delete;

import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowModelTable;
import it.academy.servlets.commands.impl.show.tables.ShowServiceCenterTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteServiceCenter implements ActionCommand {
    private ServiceCenterService centerService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long serviceId = Long.parseLong(req.getParameter(OBJECT_ID));

        try {
            centerService.deleteServiceCenter(serviceId);
        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }

        return new ShowServiceCenterTable().execute(req, resp);
    }
}
