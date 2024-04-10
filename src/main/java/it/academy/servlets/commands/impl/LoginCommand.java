package it.academy.servlets.commands.impl;

import it.academy.dto.AccountDTO;
import it.academy.dto.LoginDTO;
import it.academy.services.AuthService;
import it.academy.services.impl.AuthServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class LoginCommand implements ActionCommand {
    private AuthService service = new AuthServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LoginDTO loginDTO = LoginDTO.builder()
                .email(req.getParameter(EMAIL))
                .password(req.getParameter(PASSWORD))
                .build();

        try {
            AccountDTO accountDTO = service.loginUser(loginDTO);
            req.getSession().setAttribute(USER, accountDTO);
        } catch (Exception e) {
            req.setAttribute(ERROR, e.getMessage());
        }
        req.setAttribute(PAGE_NUMBER, FIRST_PAGE);

        return MAIN_PAGE_PATH;
    }
}
