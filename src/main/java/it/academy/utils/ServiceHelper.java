package it.academy.utils;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.entities.account.RoleEnum;
import it.academy.exceptions.common.AccessDenied;
import lombok.experimental.UtilityClass;
import static it.academy.utils.Constants.ACCESS_IS_DENIED;

@UtilityClass
public class ServiceHelper {

    public static void checkCurrentAccount(AccountDTO account) throws AccessDenied {
        if (!RoleEnum.ADMIN.equals(account.getRole())) {
            System.out.println("in createBlock " + ACCESS_IS_DENIED);
            throw new AccessDenied();
        }
    }
}
