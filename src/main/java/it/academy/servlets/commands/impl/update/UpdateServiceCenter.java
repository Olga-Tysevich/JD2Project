package it.academy.servlets.commands.impl.update;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class UpdateServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ServiceCenterDTO forUpdate = Extractor.extractObject(req, new ServiceCenterDTO());
        req.setAttribute(SERVICE_CENTER, forUpdate);

        try {
            serviceCenterService.update(forUpdate);
        } catch (ObjectAlreadyExist e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SERVICE_CENTER, forUpdate);
            req.setAttribute(FORM_PAGE, UPDATE_SERVICE_CENTER_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);
    }

}