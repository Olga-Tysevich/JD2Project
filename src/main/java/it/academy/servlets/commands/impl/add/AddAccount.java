package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowNewAccount;
import it.academy.servlets.commands.impl.show.tables.ShowAccountTable;
import it.academy.servlets.extractors.Extractor;
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
public class AddAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);
        CreateAccountDTO forCreate = Extractor.extract(req, new CreateAccountDTO());
        req.setAttribute(ACCOUNT, forCreate);
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            accountService.createAccount(forCreate);
            return new ShowAccountTable().execute(req);
        } catch (ValidationException | EmailAlreadyRegistered | EnteredPasswordsNotMatch e) {
            List<ServiceCenterDTO> serviceCenterList = serviceCenterService.findServiceCenters();
            req.setAttribute(SERVICE_CENTERS, serviceCenterList);
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    ACCOUNT_TABLE_PAGE_PATH,
                    NEW_ACCOUNT_PAGE_PATH,
                    ADD_ACCOUNT,
                    SHOW_ACCOUNT_TABLE);
        }
    }

}