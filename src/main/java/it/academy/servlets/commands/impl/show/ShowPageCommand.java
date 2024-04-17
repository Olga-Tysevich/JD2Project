package it.academy.servlets.commands.impl.show;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class ShowPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute(PAGE_NUMBER, FIRST_PAGE);
        return req.getParameter(PAGE);
    }

}