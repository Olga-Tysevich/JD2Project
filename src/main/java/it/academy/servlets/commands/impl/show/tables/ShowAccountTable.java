package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.fiterForSearch.FilterManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowAccountTable implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<String> modelFilters = FilterManager.getFiltersForAccount();
        Map<String, String> userInput = Extractor.extractParameterMap(req, modelFilters);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(ACCOUNT_FILTERS_PAGE_PATH);
        TablePage<AccountDTO> accounts = accountService.findForPage(reqData.getPageNumber(), userInput);
        CommandHelper.insertTableData(req, reqData, accounts);
        return Extractor.extractMainPagePath(req);
    }

}