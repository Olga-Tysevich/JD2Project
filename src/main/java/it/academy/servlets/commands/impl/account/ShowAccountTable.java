package it.academy.servlets.commands.impl.account;

import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_ACCOUNT_TABLE;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ShowAccountTable implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, f, c) -> adminService.findAccounts(a, f, c),
                    (i) -> adminService.findAccounts(i),
                    SHOW_ACCOUNT_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }
}