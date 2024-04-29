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
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowAccountTable implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        TableReq dataForPage = Extractor.extractDataForTable(req);
        ListForPage<AccountDTO> accounts = accountService.findAccounts(
                dataForPage.getPageNumber(),
                dataForPage.getFilter(),
                dataForPage.getInput());
        CommandHelper.setTableData(req, dataForPage, accounts);
        log.info(CURRENT_TABLE, accounts);
        return ADMIN_MAIN_PAGE_PATH;
    }

}