package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.resp.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class ShowAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long accountId = Long.parseLong(req.getParameter(OBJECT_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        String tablePage = req.getParameter(PAGE);

        AccountDTO account = accountService.findAccount(accountId);
        req.setAttribute(ACCOUNT, account);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(PAGE, tablePage);

        return ACCOUNT_PAGE_PATH;
    }

}
