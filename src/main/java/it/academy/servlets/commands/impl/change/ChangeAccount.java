package it.academy.servlets.commands.impl.change;

import it.academy.dto.account.ChangeAccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

@Slf4j
public class ChangeAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ChangeAccountDTO forUpdate = Extractor.extract(req, new ChangeAccountDTO());
        req.setAttribute(ACCOUNT, forUpdate);
        String errorMessage = accountService.update(forUpdate);

        if (!StringUtils.isBlank(errorMessage)) {
            return CommandHelper.insertFormData(req, ACCOUNT_PAGE_PATH, CHANGE_ACCOUNT);
        }

        return Extractor.extractLastPage(req);
    }

}