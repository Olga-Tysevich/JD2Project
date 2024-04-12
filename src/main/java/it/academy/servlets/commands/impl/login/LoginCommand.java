package it.academy.servlets.commands.impl.login;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.login.req.LoginDTO;
import it.academy.services.auth.AuthService;
import it.academy.services.auth.AuthServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

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
        } catch (Exception e) {
            req.setAttribute(ERROR, e.getMessage());
            System.out.println(e.getMessage());
            return LOGIN_PAGE_PATH;
        }
        req.setAttribute(PAGE_NUMBER, FIRST_PAGE);

        return MAIN_PAGE_PATH;
    }
}
