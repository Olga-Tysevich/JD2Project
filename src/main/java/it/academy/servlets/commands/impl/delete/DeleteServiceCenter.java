package it.academy.servlets.commands.impl.delete;

import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long serviceCenterId = Extractor.extractLongVal(req, OBJECT_ID, null);
            serviceCenterService.delete(serviceCenterId);
        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }
        return Extractor.extractLastPage(req);
    }

}
