package it.academy.servlets.commands.impl;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;
import static it.academy.utils.Constants.*;

public class AddCommand<T> implements ActionCommand {
    private Consumer<T> methodForSave;
    private T dto;
    private ActionCommand commandForDisplayTable;

    public AddCommand(Consumer<T> methodForSave, T dto, ActionCommand commandForDisplayTable) {
        this.methodForSave = methodForSave;
        this.dto = dto;
        this.commandForDisplayTable = commandForDisplayTable;
    }

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        T dto = ExtractorImpl.extract(req, this.dto);
        try {
            methodForSave.accept(dto);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return commandForDisplayTable.execute(req);
    }

}