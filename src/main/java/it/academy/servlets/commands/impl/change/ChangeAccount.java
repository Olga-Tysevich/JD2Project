package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.ValidationException;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.TableExtractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.MessageConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        CommandHelper.checkRole(currentAccount);

        try {
            ChangeAccountDTO forUpdate = Extractor.extract(req, new ChangeAccountDTO());
            TableExtractor.extract(req);
            log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);
            accountService.updateAccount(forUpdate);
            return MAIN_PAGE_PATH;
        } catch (EmailAlreadyRegistered | ValidationException e) {
            req.setAttribute(ACCOUNT, currentAccount);
            req.setAttribute(ERROR, e.getMessage());
            return ACCOUNT_PAGE_PATH;
        }
    }

}