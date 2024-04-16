package it.academy.servlets.commands.factory;

import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

@Slf4j
public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current;

        String action = req.getParameter(COMMAND);
        log.info(String.format(CURRENT_CLASS,  this.getClass().getSimpleName()));
        log.info(String.format(CURRENT_ACTION, action));

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
            log.info(String.format(CURRENT_COMMAND, current));
        } catch (IllegalArgumentException e) {
            log.error(String.format(UNKNOWN_COMMAND, action));
            throw e;
        }

        return current;
    }
}
