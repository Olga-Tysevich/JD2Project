package it.academy.servlets.commands.impl.change;

import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.forms.GetAccount;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class ChangeAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO forUpdate = Extractor.extract(req, new AccountDTO());
        req.setAttribute(ACCOUNT, forUpdate);
        AccountDTO accountDTO = accountService.update(forUpdate);
        String errorMessage = accountDTO.getErrorMessage();

        if (!StringUtils.isBlank(errorMessage)) {
            req.setAttribute(ERROR, accountDTO.getErrorMessage());
            req.setAttribute(ACCOUNT, accountDTO);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new GetAccount().execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }
}