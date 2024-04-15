package it.academy.servlets.commands.impl.show.tables;

import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.TableExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT_TABLE;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ShowAccountTable implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, p, f, c) -> adminService.findAccounts(a, p, f, c),
                    (a, p) -> adminService.findAccounts(a, p),
                    SHOW_ACCOUNT_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }
}