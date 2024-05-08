package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_SERVICE_CENTER_PAGE_PATH;

public class AddServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        ServiceCenterDTO forCreate = Extractor.extractObject(req, new ServiceCenterDTO());
        req.setAttribute(SERVICE_CENTER, forCreate);

        try {
            serviceCenterService.create(forCreate);
        } catch (ObjectAlreadyExist e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SERVICE_CENTER, forCreate);
            req.setAttribute(FORM_PAGE, ADD_SERVICE_CENTER_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);

    }

}
