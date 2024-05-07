package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.NEW_SERVICE_CENTER_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        ServiceCenterDTO forCreate = Extractor.extract(req, new ServiceCenterDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);
        req.setAttribute(SERVICE_CENTER, forCreate);

        try {
            serviceCenterService.create(forCreate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SERVICE_CENTER, forCreate);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(FORM_PAGE, NEW_SERVICE_CENTER_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);

    }

}
