package it.academy.utils;

import it.academy.dto.ListForPage;
import it.academy.dto.TableReq;
import it.academy.dto.account.AccountDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.DISPLAY_TABLE_COMMAND;
import static it.academy.utils.constants.JSPConstant.CHANGE_FORM_PAGE_PATH;
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

    public static <T> void setTableData(HttpServletRequest req, TableReq dataForPage, ListForPage<T> listForTable) {
        listForTable.setPage(dataForPage.getPage());
        listForTable.setCommand(dataForPage.getCommand());
        listForTable.setLastFilter(dataForPage.getFilter());
        listForTable.setLastInput(dataForPage.getInput());
        req.setAttribute(LIST_FOR_PAGE, listForTable);
    }

    public static String insertFormData(HttpServletRequest request, String tablePagePath, String formPagePath,
                                      CommandEnum commandForChange, CommandEnum displayTableCommand) {

        int pageNumber = Extractor.extractIntVal(request, PAGE_NUMBER, FIRST_PAGE);

        request.setAttribute(PAGE, tablePagePath);
        request.setAttribute(FORM_PAGE, formPagePath);
        request.setAttribute(COMMAND, commandForChange);
        request.setAttribute(DISPLAY_TABLE_COMMAND, displayTableCommand);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        return CHANGE_FORM_PAGE_PATH;
    }
}
