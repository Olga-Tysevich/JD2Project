package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.FORM_PAGE;
import static it.academy.utils.constants.JSPConstant.NEW_ACCOUNT_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTERS;

public class GetNewAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        try {
            String errorMessage = Extractor.extractString(req, ERROR, StringUtils.EMPTY);
            if (!StringUtils.isBlank(errorMessage)) {
                AccountDTO accountDTO = Builder.buildEmptyAccount();
                req.setAttribute(ACCOUNT, accountDTO);
            }
            Map<Long, String> serviceCenters = accountService.getServiceCentersForAccountForm();
            req.setAttribute(SERVICE_CENTERS, serviceCenters);
        } catch (ObjectNotFound e) {
            req.setAttribute(ERROR, SERVICE_CENTERS_NOT_FOUND);
        }
        req.setAttribute(FORM_PAGE, NEW_ACCOUNT_PAGE_PATH);
        return Extractor.extractLastPage(req);
    }
}
