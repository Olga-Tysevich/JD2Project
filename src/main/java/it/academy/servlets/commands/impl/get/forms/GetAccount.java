package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.AccountService;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.JSPConstant.NEW_ACCOUNT_PAGE_PATH;

@Slf4j
public class GetAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandEnum command = CommandEnum.valueOf(req.getParameter(COMMAND));
        switch (command) {
            case GET_NEW_ACCOUNT:
                return showNewAccount(req);
            case GET_ACCOUNT:
                return showAccount(req);
            default:
                return ADMIN_MAIN_PAGE_PATH;
        }
    }

    private String showNewAccount(HttpServletRequest req) {
        CommandHelper.checkRole(req);
        try {
            CreateAccountDTO createAccountDTO = Builder.buildEmptyAccount();
            Map<Long, String> serviceCenters = accountService.getServiceCentersForAccountForm();
            req.setAttribute(ACCOUNT, createAccountDTO);
            req.setAttribute(SERVICE_CENTERS, serviceCenters);
            return NEW_ACCOUNT_PAGE_PATH;
        } catch (ObjectNotFound e) {
            req.setAttribute(ERROR, SERVICE_CENTERS_NOT_FOUND);
            return ADMIN_MAIN_PAGE_PATH;
        }
    }

    private String showAccount(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(OBJECT_ID));
        AccountDTO account = accountService.find(accountId);
        req.setAttribute(ACCOUNT, account);
        return CommandHelper.insertFormData(req, ACCOUNT_PAGE_PATH, CHANGE_ACCOUNT);
    }

}
