package it.academy.servlets.commands.impl.delete;

import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long accountId = Extractor.extractLongVal(req, OBJECT_ID, null);
            accountService.delete(accountId);
        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }
        return Extractor.extractLastPage(req);
    }
}
