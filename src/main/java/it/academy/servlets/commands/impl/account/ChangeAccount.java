package it.academy.servlets.commands.impl.account;

import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import javax.servlet.http.HttpServletRequest;


import java.lang.reflect.InvocationTargetException;

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
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException | NoSuchFieldException e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}