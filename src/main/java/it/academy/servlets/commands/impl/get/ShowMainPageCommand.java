package it.academy.servlets.commands.impl.get;

import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.*;

@Slf4j
public class ShowMainPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return (String) req.getSession().getAttribute(MAIN_PAGE_PATH);
    }

}