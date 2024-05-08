package it.academy.utils;

import it.academy.dto.TablePage;
import it.academy.dto.TablePage2;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.*;

@UtilityClass
@Slf4j
public class CommandHelper {

    public static void checkRole(HttpServletRequest request) {
        AccountDTO currentAccount = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
        log.info(CURRENT_ACCOUNT_PATTERN, currentAccount);
        if (!RoleEnum.ADMIN.equals(currentAccount.getRole())) {
            throw new AccessDenied();
        }
    }



    public static <T> void setTableData(HttpServletRequest req, TablePageReq dataForPage, TablePage<T> listForTable) {
        listForTable.setPage(dataForPage.getPage());
        listForTable.setCommand(dataForPage.getCommand());
        listForTable.setLastFilter(dataForPage.getFilter());
        listForTable.setLastInput(dataForPage.getInput());
        req.setAttribute(TABLE_PAGE, listForTable);
    }

    public static <T> void insertTableData(HttpServletRequest req, TablePageReq dataForPage, TablePage2<T> listForTable) {
        req.setAttribute(COMMAND, dataForPage.getCommand());
        req.setAttribute(PAGE, dataForPage.getPage());
        req.setAttribute(PAGE_NUMBER, dataForPage.getPageNumber());
        req.setAttribute(FILTER, dataForPage.getFilter());
        req.setAttribute(USER_INPUT, dataForPage.getInput());
        req.setAttribute(TABLE_PAGE, listForTable);
    }

    public static String insertFormData(HttpServletRequest request, String tablePagePath, String formPagePath,
                                        CommandEnum commandForChange, CommandEnum displayTableCommand) {

        request.setAttribute(COMMAND, commandForChange);
        request.setAttribute(FORM_PAGE, formPagePath);
        return CHANGE_FORM_PAGE_PATH;
    }

    public static String insertFormData(HttpServletRequest request, String formPagePath, CommandEnum commandForChange) {

        request.setAttribute(COMMAND, commandForChange);
        request.setAttribute(FORM_PAGE, formPagePath);
        return CHANGE_FORM_PAGE_PATH;
    }
}
