package it.academy.servlets.commands.impl.login;

import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.LAST_PAGE;
import static it.academy.utils.constants.JSPConstant.MAIN_PAGE_PATH;

public class LogOutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        req.getSession().removeAttribute(ACCOUNT);
        req.getSession().removeAttribute(LAST_PAGE);
        req.getSession().removeAttribute(MAIN_PAGE_PATH);

        return MAIN_PAGE_PATH;
    }

}
