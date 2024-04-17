package it.academy.servlets.commands.impl.show.tables;

import it.academy.services.admin.AdminService;
import it.academy.services.admin.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ShowAccountTable implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

//        try {
//            return TableExtractor.extract(req,
//                    (a, p, f, c) -> adminService.findAccounts(a, p, f, c),
//                    (a, p) -> adminService.findAccounts(a, p),
//                    SHOW_ACCOUNT_TABLE);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return ERROR_PAGE_PATH;
//        }
        return null;
    }
}