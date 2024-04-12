package it.academy.servlets.commands.impl.account;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ChangeAccountDTO account = Extractor.extract(req, new ChangeAccountDTO());

        adminService.updateAccount(account);

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

        return MAIN_PAGE_PATH;
    }

}