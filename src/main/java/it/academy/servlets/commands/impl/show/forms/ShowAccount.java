package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ACCOUNT_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.ACCOUNT_TABLE_PAGE_PATH;

@Slf4j
public class ShowAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);

        long accountId = Long.parseLong(req.getParameter(OBJECT_ID));

        AccountDTO account = accountService.findAccount(accountId);
        req.setAttribute(ACCOUNT, account);

        return CommandHelper.insertFormData(req,
                ACCOUNT_TABLE_PAGE_PATH,
                ACCOUNT_PAGE_PATH,
                CHANGE_ACCOUNT,
                SHOW_ACCOUNT_TABLE);
    }

}
