package it.academy.servlets.commands.impl.get.forms;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.NEW_SERVICE_CENTER_PAGE_PATH;

public class GetNewServiceCenter implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        req.setAttribute(SERVICE_CENTER, Builder.buildEmptyServiceCenter());
        req.setAttribute(FORM_PAGE, NEW_SERVICE_CENTER_PAGE_PATH);
        return Extractor.extractLastPage(req);

    }

}
