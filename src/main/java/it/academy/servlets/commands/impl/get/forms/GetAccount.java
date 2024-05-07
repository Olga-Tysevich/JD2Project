package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ACCOUNT_PAGE_PATH;

@Slf4j
public class GetAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long accountId = Long.parseLong(req.getParameter(OBJECT_ID));
        String errorMessage = Extractor.extractString(req, ERROR, StringUtils.EMPTY);

        if (StringUtils.isBlank(errorMessage)) {
            AccountDTO account = accountService.find(accountId);
            req.setAttribute(ACCOUNT, account);
        }

        req.setAttribute(FORM_PAGE, ACCOUNT_PAGE_PATH);
        return Extractor.extractLastPage(req);
    }

}
