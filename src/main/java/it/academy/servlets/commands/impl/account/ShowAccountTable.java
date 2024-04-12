package it.academy.servlets.commands.impl.account;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.req.TableReq;
import it.academy.dto.table.resp.ListForPage;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_ACCOUNT_TABLE;
import static it.academy.utils.Constants.*;

public class ShowAccountTable implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        TableReq request = Extractor.extract(req, new TableReq());
        System.out.println("show account table req " + request);

        ListForPage<AccountDTO> accounts;
        if (request.getInput() != null && !request.getInput().isBlank()
                && request.getFilter() != null && !request.getFilter().isBlank()) {
            accounts = adminService.findAccounts(request.getPageNumber(), request.getFilter(), request.getInput());
        } else {
            accounts = adminService.findAccounts(request.getPageNumber());
        }
        System.out.println("show account table accounts " + accounts);
        accounts.setPage(ACCOUNT_TABLE_PAGE_PATH);
        accounts.setCommand(SHOW_ACCOUNT_TABLE.name());
        System.out.println("show accounts request after find" + request);

        req.setAttribute(LIST_FOR_PAGE, accounts);

        return MAIN_PAGE_PATH;
    }

}