package it.academy.servlets.commands.factory;

import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.COMMAND;


public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current = null;

        String action = req.getParameter(COMMAND);
        System.out.println("action factory command " + action);

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return current;
    }
}
