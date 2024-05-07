package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.forms.GetNewAccount;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class AddAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        AccountDTO forCreate = Extractor.extract(req, new AccountDTO());
        log.info(OBJECT_FOR_SAVE_PATTERN, forCreate);
        req.setAttribute(ACCOUNT, forCreate);
        AccountDTO accountForm = accountService.create(forCreate);
        String message = accountForm.getErrorMessage();

        if (!StringUtils.isBlank(message)) {
            req.setAttribute(ERROR, accountForm.getErrorMessage());
            req.setAttribute(ACCOUNT, accountForm);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new GetNewAccount().execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }

}