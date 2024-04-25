package it.academy.servlets.commands.impl.login;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.LoginDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.account.Account;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserIsBlocked;
import it.academy.exceptions.auth.UserNotFound;
import it.academy.services.account.AuthService;
import it.academy.services.account.impl.AuthServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;

@Slf4j
public class LoginCommand implements ActionCommand {
    private AuthService service = new AuthServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LoginDTO loginDTO = LoginDTO.builder()
                .email(req.getParameter(EMAIL))
                .password(req.getParameter(PASSWORD))
                .build();
        log.info(OBJECT_CREATED_PATTERN, loginDTO);
        try {
            AccountDTO accountDTO = service.loginUser(loginDTO);
            req.getSession().setAttribute(ACCOUNT, accountDTO);
            req.getSession().setAttribute(ROLE, accountDTO.getRole());
            ListForPage<Account> list = new ListForPage<>();
            list.setPageNumber(FIRST_PAGE);
            req.setAttribute(LIST_FOR_PAGE, list);

            return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
        } catch (UserNotFound | IncorrectPassword | UserIsBlocked e) {
            req.setAttribute(ERROR, e.getMessage());
            return LOGIN_PAGE_PATH;
        }
    }
}
