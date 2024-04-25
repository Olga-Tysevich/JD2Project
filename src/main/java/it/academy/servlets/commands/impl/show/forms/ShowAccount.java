package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.TableReq;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.JSPConstant.NEW_ACCOUNT_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        CommandEnum command = CommandEnum.valueOf(req.getParameter(COMMAND));
        switch (command) {
            case SHOW_NEW_ACCOUNT: return showNewAccount(req);
            case SHOW_ACCOUNT: return showAccount(req);
            default: return ADMIN_MAIN_PAGE_PATH;
        }
    }

    private String showNewAccount(HttpServletRequest req) {

        CreateAccountDTO createAccountDTO = Builder.buildEmptyAccount();

        List<ServiceCenterDTO> serviceCenterList = serviceCenterService.findServiceCenters();
        if (!serviceCenterList.isEmpty()) {
            TableReq pageData = Extractor.extract(req, new TableReq());
            log.info(OBJECT_EXTRACTED_PATTERN, pageData);
            req.setAttribute(ACCOUNT, createAccountDTO);
            req.setAttribute(SERVICE_CENTERS, serviceCenterList);
            return insertAttributes(req, NEW_ACCOUNT_PAGE_PATH, ADD_ACCOUNT);
        }
        req.setAttribute(ERROR, SERVICE_CENTERS_NOT_FOUND);
        return ADMIN_MAIN_PAGE_PATH;
    }

    private String showAccount(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(OBJECT_ID));

        AccountDTO account = accountService.findAccount(accountId);
        req.setAttribute(ACCOUNT, account);
        return insertAttributes(req, ACCOUNT_PAGE_PATH, CHANGE_ACCOUNT);
    }

    private String insertAttributes(HttpServletRequest req, String pagePath, CommandEnum command) {

        return CommandHelper.insertFormData(req,
                ACCOUNT_TABLE_PAGE_PATH,
                pagePath,
                command,
                SHOW_ACCOUNT_TABLE);
    }

}
