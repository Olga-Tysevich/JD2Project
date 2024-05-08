package it.academy.servlets.commands.impl.show;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowMainPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return Extractor.extractMainPagePath(req);
    }

}