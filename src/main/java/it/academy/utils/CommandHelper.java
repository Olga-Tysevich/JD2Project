package it.academy.utils;

import it.academy.dto.account.AccountDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.servlets.commands.factory.CommandEnum;
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

    public static String insertFormData(HttpServletRequest request, String tablePagePath, String formPagePath,
                                      CommandEnum commandForChange, CommandEnum displayTableCommand) {

        int pageNumber = request.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        request.setAttribute(PAGE, tablePagePath);
        log.info(CURRENT_PAGE_PATTERN, tablePagePath);
        request.setAttribute(FORM_PAGE, formPagePath);
        log.info(FORM_PAGE_PATTERN, formPagePath);
        request.setAttribute(COMMAND, commandForChange);
        log.info(CURRENT_COMMAND, commandForChange);
        request.setAttribute(DISPLAY_TABLE_COMMAND, displayTableCommand);
        log.info(DISPLAY_TABLE_PATTERN, displayTableCommand);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        log.info(CURRENT_PAGE_NUMBER_PATTERN, pageNumber);
        return CHANGE_FORM_PAGE_PATH;
    }
}
