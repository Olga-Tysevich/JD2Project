package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_PAGE_PATH;

public class GetServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Long id = Extractor.extractLongVal(req, OBJECT_ID, null);
        ServiceCenterDTO serviceCenterDTO = serviceCenterService.find(id);
        req.setAttribute(SERVICE_CENTER, serviceCenterDTO);
        req.setAttribute(FORM_PAGE, SERVICE_CENTER_PAGE_PATH);
        return Extractor.extractLastPage(req);
    }

}
