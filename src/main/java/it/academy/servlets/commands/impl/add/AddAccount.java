package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.AccountFormDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        CreateAccountDTO forCreate = Extractor.extract(req, new CreateAccountDTO());
        req.setAttribute(ACCOUNT, forCreate);
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);
        AccountFormDTO accountFormDTO = accountService.createAccount(forCreate);

        req.setAttribute(ERROR, accountFormDTO.getMessage());
        req.setAttribute(SERVICE_CENTERS, accountFormDTO.getServiceCenters());

        return NEW_ACCOUNT_PAGE_PATH;
    }

}