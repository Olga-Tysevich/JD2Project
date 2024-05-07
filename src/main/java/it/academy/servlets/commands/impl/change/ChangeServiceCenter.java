package it.academy.servlets.commands.impl.change;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ServiceCenterDTO forUpdate = Extractor.extract(req, new ServiceCenterDTO());
        req.setAttribute(SERVICE_CENTER, forUpdate);
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            serviceCenterService.update(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SERVICE_CENTER, forUpdate);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(FORM_PAGE, SERVICE_CENTER_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);
    }

}