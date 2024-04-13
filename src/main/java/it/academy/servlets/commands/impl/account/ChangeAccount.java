package it.academy.servlets.commands.impl.account;

import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> adminService.updateAccount((ChangeAccountDTO) a)),
                    (id) -> adminService.findAccount((Long) id),
                    ChangeAccountDTO.class,
                    ACCOUNT,
                    ACCOUNT_PAGE_PATH,
                    () -> new ShowAccountTable().execute(req));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }

}