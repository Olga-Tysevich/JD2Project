package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.TableReq;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_ACCOUNT;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowNewAccount implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);

        CreateAccountDTO createAccountDTO = Builder.buildEmptyAccount();

        List<ServiceCenterDTO> serviceCenterList = serviceCenterService.findServiceCenters();
        if (!serviceCenterList.isEmpty()) {
            TableReq pageData = Extractor.extract(req, new TableReq());
            log.info(OBJECT_EXTRACTED_PATTERN, pageData);
            req.setAttribute(ACCOUNT, createAccountDTO);
            req.setAttribute(SERVICE_CENTERS, serviceCenterList);
            return CommandHelper.insertFormData(req,
                    ACCOUNT_TABLE_PAGE_PATH,
                    NEW_ACCOUNT_PAGE_PATH,
                    ADD_ACCOUNT,
                    SHOW_ACCOUNT_TABLE);
        }
        req.setAttribute(ERROR, SERVICE_CENTERS_NOT_FOUND);
        return MAIN_PAGE_PATH;
    }

}
