package it.academy.servlets.commands.impl.account;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.dto.table.req.TableReq;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.Extractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;


import static it.academy.utils.Constants.*;

public class ChangeAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ChangeAccountDTO account = Extractor.extract(req, new ChangeAccountDTO());
        TableReq request = Extractor.extract(req, new TableReq());
        System.out.println("change account req " + request);
        System.out.println("change account account " + account);

        try {
            adminService.updateAccount(account);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());

            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(PAGE, req.getParameter(PAGE));
            req.setAttribute(ACCOUNT, adminService.findAccount(account.getId()));
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return ACCOUNT_PAGE_PATH;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowAccountTable().execute(req);
    }

}