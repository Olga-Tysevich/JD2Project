package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.entities.account.Account_;
import it.academy.services.account.AccountService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowAccountTable implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage<AccountDTO> accounts;
        switch (filter) {
            case StringUtils.EMPTY:
                accounts = accountService.findForPage(reqData.getPageNumber());
                break;
            case Account_.SERVICE_CENTER:
                accounts = accountService.findForPageByServiceCenter(reqData.getPageNumber(),
                        reqData.getInput());
                break;
            default:
                accounts = accountService.findForPageByFilter(reqData.getPageNumber(),
                        reqData.getFilter(), reqData.getInput());
        }

        CommandHelper.insertTableData(req, reqData, accounts);
        log.info(CURRENT_TABLE, accounts);
        return ADMIN_MAIN_PAGE_PATH;
    }

}