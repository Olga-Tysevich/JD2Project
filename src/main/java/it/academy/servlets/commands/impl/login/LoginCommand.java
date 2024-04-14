package it.academy.servlets.commands.impl.login;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.req.LoginDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Account;
import it.academy.services.AuthService;
import it.academy.services.impl.AuthServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

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
        ListForPage<Account> list = new ListForPage<>();
        list.setPageNumber(FIRST_PAGE);
        req.setAttribute(LIST_FOR_PAGE, list);

        return MAIN_PAGE_PATH;
    }
}
