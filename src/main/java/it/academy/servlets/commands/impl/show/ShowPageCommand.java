package it.academy.servlets.commands.impl.show;

import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.MessageConstants.CURRENT_PAGE_PATTERN;

@Slf4j
public class ShowPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute(PAGE_NUMBER, FIRST_PAGE);
        String page = req.getParameter(PAGE);
        log.info(CURRENT_PAGE_PATTERN, page);
        return page;
    }

}