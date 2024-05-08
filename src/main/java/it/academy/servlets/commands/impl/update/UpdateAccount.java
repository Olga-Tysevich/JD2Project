package it.academy.servlets.commands.impl.update;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.UPDATE_ACCOUNT_PAGE_PATH;

public class UpdateAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO forUpdate = Extractor.extractObject(req, new AccountDTO());
        req.setAttribute(ACCOUNT, forUpdate);
        AccountDTO accountDTO = accountService.update(forUpdate);
        String errorMessage = accountDTO.getErrorMessage();

        if (!StringUtils.isBlank(errorMessage)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(ERROR, accountDTO.getErrorMessage());
            req.setAttribute(ACCOUNT, accountDTO);
            req.setAttribute(FORM_PAGE, UPDATE_ACCOUNT_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);
    }
}