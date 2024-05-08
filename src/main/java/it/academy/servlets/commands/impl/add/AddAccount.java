package it.academy.servlets.commands.impl.add;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowAddAccount;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;

public class AddAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        AccountDTO forCreate = Extractor.extractObject(req, new AccountDTO());
        req.setAttribute(ACCOUNT, forCreate);
        AccountDTO accountForm = accountService.create(forCreate);
        String message = accountForm.getErrorMessage();

        if (!StringUtils.isBlank(message)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(ERROR, accountForm.getErrorMessage());
            req.setAttribute(ACCOUNT, accountForm);
            return new ShowAddAccount().execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }

}