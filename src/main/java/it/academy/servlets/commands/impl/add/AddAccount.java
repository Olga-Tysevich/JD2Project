package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.req.ServiceCenterDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowAccountTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);

        try {
            CreateAccountDTO forCreate = Extractor.extract(req, new CreateAccountDTO());
            req.setAttribute(ACCOUNT, forCreate);
            log.info(OBJECT_EXTRACTED_PATTERN, forCreate);
            accountService.createAccount(forCreate);
            return new ShowAccountTable().execute(req);
        } catch (ValidationException | EmailAlreadyRegistered | EnteredPasswordsNotMatch e) {
            List<ServiceCenterDTO> serviceCenterList = serviceCenterService.findServiceCenters();
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SERVICE_CENTERS, serviceCenterList);
            return NEW_ACCOUNT_PAGE_PATH;
        }
    }

}