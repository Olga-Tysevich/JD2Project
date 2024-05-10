package it.academy.utils;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.LAST_PAGE;
import static it.academy.utils.constants.LoggerConstants.*;

@UtilityClass
@Slf4j
public class CommandHelper {

    public void checkRole(HttpServletRequest request) {
        AccountDTO currentAccount = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
        log.info(CURRENT_ACCOUNT_PATTERN, currentAccount);
        if (!RoleEnum.ADMIN.equals(currentAccount.getRole())) {
            throw new AccessDenied();
        }
    }

    public <T> void insertTableData(HttpServletRequest req, TablePageReq dataForPage, TablePage<T> listForTable) {
        req.setAttribute(COMMAND, dataForPage.getCommand());
        req.setAttribute(PAGE, dataForPage.getPage());
        req.setAttribute(PAGE_NUMBER, dataForPage.getPageNumber());
        req.setAttribute(FILTER_PAGE, dataForPage.getFilterPage());
        req.setAttribute(USER_INPUT, dataForPage.getUserInput());
        req.setAttribute(TABLE_PAGE, listForTable);
    }

}
