package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowAccountTable implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        ListForPage<AccountDTO> accounts = accountService.findAccounts(
                    dataFromPage.getPageNumber(),
                    dataFromPage.getFilter(),
                    dataFromPage.getInput());
        accounts.setPage(dataFromPage.getPage());
        accounts.setCommand(SHOW_ACCOUNT_TABLE.name());
        req.setAttribute(LIST_FOR_PAGE, accounts);
        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
        log.info(CURRENT_TABLE, accounts);
        return ADMIN_MAIN_PAGE_PATH;
    }

}