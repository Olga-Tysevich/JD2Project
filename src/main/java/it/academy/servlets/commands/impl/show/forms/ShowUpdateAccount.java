package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.UPDATE_ACCOUNT_PAGE_PATH;

public class ShowUpdateAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long accountId = Extractor.extractLongVal(req, OBJECT_ID, null);
        AccountDTO account = accountService.find(accountId);
        req.setAttribute(ACCOUNT, account);
        return new ShowForm(UPDATE_ACCOUNT_PAGE_PATH).execute(req, resp);
    }

}
