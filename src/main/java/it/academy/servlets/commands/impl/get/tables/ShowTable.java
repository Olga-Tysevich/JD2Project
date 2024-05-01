package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.TABLE_PAGE;

public abstract class ShowTable implements ActionCommand {

    protected<T> void setTableData(HttpServletRequest req, TablePageReq dataForPage, TablePage<T> listForTable) {
        listForTable.setPage(dataForPage.getPage());
        listForTable.setCommand(dataForPage.getCommand());
        listForTable.setLastFilter(dataForPage.getFilter());
        listForTable.setLastInput(dataForPage.getInput());
        req.setAttribute(TABLE_PAGE, listForTable);
    }
}
