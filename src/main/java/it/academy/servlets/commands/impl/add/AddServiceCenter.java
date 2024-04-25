package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowServiceCenterTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_TABLE_PAGE_PATH;
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
            serviceCenterService.createServiceCenter(forCreate);
            return new ShowServiceCenterTable().execute(req, resp);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    SERVICE_CENTER_TABLE_PAGE_PATH,
                    SERVICE_CENTER_PAGE_PATH,
                    ADD_SERVICE_CENTER,
                    SHOW_SERVICE_CENTER_TABLE);
        }

    }

}
