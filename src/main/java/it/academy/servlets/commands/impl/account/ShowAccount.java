package it.academy.servlets.commands.impl.account;

import it.academy.dto.resp.AccountDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(OBJECT_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));

        AccountDTO account = adminService.findAccount(accountId);
        System.out.println("show account ac " + account);
        req.setAttribute(ACCOUNT, account);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return ACCOUNT_PAGE_PATH;
    }

}
