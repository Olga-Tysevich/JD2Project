package it.academy.utils;

import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.ACCOUNT;
import static it.academy.utils.constants.LoggerConstants.CURRENT_ACCOUNT_PATTERN;

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
}
