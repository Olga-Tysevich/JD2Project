package it.academy.servlets.commands.impl.login;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.req.LoginDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Account;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserIsBlocked;
import it.academy.exceptions.auth.UserNotFound;
import it.academy.services.account.AuthService;
import it.academy.services.account.impl.AuthServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class LoginCommand implements ActionCommand {
    private AuthService service = new AuthServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        LoginDTO loginDTO = LoginDTO.builder()
                .email(req.getParameter(EMAIL))
                .password(req.getParameter(PASSWORD))
                .build();
        try {
            AccountDTO accountDTO = service.loginUser(loginDTO);
            req.getSession().setAttribute(ACCOUNT, accountDTO);
            req.getSession().setAttribute(ROLE, accountDTO.getRole());
        } catch (UserNotFound | IncorrectPassword | UserIsBlocked e) {
            req.setAttribute(ERROR, e.getMessage());
            return LOGIN_PAGE_PATH;
        }
        ListForPage<Account> list = new ListForPage<>();
        list.setPageNumber(FIRST_PAGE);
        req.setAttribute(LIST_FOR_PAGE, list);

        return MAIN_PAGE_PATH;
    }
}
