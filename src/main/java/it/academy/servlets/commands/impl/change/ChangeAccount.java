package it.academy.servlets.commands.impl.change;

import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.ValidationException;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowAccount;
import it.academy.servlets.commands.impl.show.tables.ShowAccountTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ACCOUNT_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        CommandHelper.checkRole(req);

        try {
            ChangeAccountDTO forUpdate = Extractor.extract(req, new ChangeAccountDTO());
            log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);
            accountService.updateAccount(forUpdate);
            return new ShowAccountTable().execute(req);
        } catch (EmailAlreadyRegistered | ValidationException e) {
            req.setAttribute(ACCOUNT, currentAccount);
            req.setAttribute(ERROR, e.getMessage());
            return new ShowAccount().execute(req);
        }
    }

}