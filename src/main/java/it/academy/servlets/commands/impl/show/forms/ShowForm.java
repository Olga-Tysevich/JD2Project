package it.academy.servlets.commands.impl.show.forms;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.FORM_PAGE;

public class ShowForm implements ActionCommand {
    private String pagePath;

    public ShowForm(String pagePath) {
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        req.setAttribute(FORM_PAGE, pagePath);
        return Extractor.extractLastPage(req);
    }
}
