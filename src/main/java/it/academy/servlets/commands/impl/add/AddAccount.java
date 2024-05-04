package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.AccountFormDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

@Slf4j
public class AddAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        CreateAccountDTO forCreate = Extractor.extract(req, new CreateAccountDTO());
        req.setAttribute(ACCOUNT, forCreate);
        AccountFormDTO accountFormDTO = accountService.create(forCreate);
        String message = accountFormDTO.getMessage();

        if (!StringUtils.isBlank(message)) {
            req.setAttribute(ERROR, accountFormDTO.getMessage());
            req.setAttribute(SERVICE_CENTERS, accountFormDTO.getServiceCenters());
            return NEW_ACCOUNT_PAGE_PATH;
        }

        return Extractor.extractLastPage(req);
    }

}