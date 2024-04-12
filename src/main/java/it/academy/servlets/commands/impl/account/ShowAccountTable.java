package it.academy.servlets.commands.impl.account;

import it.academy.dto.ListForPage;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowAccountTable implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<AccountDTO> accounts;
        if (input != null && !input.isBlank()) {
            accounts = adminService.findAccounts(pageNumber, filter, input);
        } else {
            accounts = adminService.findAccounts(pageNumber);
        }

//        PageManager.insertAttributesForTable(req, accounts, ACCOUNT_TABLE_PAGE_PATH);
        req.setAttribute(MAX_PAGE, accounts.getMaxPageNumber());
        req.setAttribute(COMMAND, SHOW_ACCOUNT_TABLE);

        return MAIN_PAGE_PATH;
    }

}