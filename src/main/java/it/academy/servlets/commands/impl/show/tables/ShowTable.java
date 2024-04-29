package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.ListForPage;
import it.academy.dto.TableReq;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.LIST_FOR_PAGE;

public abstract class ShowTable implements ActionCommand {

    protected<T> void setTableData(HttpServletRequest req, TableReq dataForPage, ListForPage<T> listForTable) {
        listForTable.setPage(dataForPage.getPage());
        listForTable.setCommand(dataForPage.getCommand());
        listForTable.setLastFilter(dataForPage.getFilter());
        listForTable.setLastInput(dataForPage.getInput());
        req.setAttribute(LIST_FOR_PAGE, listForTable);
    }
}
