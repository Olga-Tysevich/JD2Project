package it.academy.servlets.commands.impl.show;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class ShowPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {

        String displayTable = req.getParameter(DISPLAY_TABLE_COMMAND);
        log.info(OBJECT_EXTRACTED_PATTERN, displayTable);

        ActionCommand displayTableCommand;
        if (displayTable != null) {
            displayTableCommand = CommandEnum.valueOf(displayTable).getCurrentCommand();
            log.info(CURRENT_COMMAND, displayTableCommand);
            return displayTableCommand.execute(req);
        }

        req.setAttribute(PAGE_NUMBER, FIRST_PAGE);
        String page = req.getParameter(PAGE);
        log.info(CURRENT_PAGE_PATTERN, page);
        return page;
    }

}