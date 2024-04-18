package it.academy.servlets.commands.factory;

import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.MessageConstants.UNKNOWN_COMMAND;

@Slf4j
public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current;

        String action = req.getParameter(COMMAND);

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.error(UNKNOWN_COMMAND, action);
            throw e;
        }

        return current;
    }
}
