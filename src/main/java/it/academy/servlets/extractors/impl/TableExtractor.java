package it.academy.servlets.extractors.impl;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.req.TableReq;
import it.academy.dto.resp.ListForPage;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.interfaces.ActiveEntitySupplier;
import it.academy.utils.interfaces.FilteredEntitySupplier;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

@UtilityClass
public class TableExtractor{

    public static<T> String extract(HttpServletRequest req, FilteredEntitySupplier<ListForPage<T>> methodWithFilters,
                                    ActiveEntitySupplier<ListForPage<T>> methodWithoutFilters, CommandEnum command) {

        TableReq request = ExtractorImpl.extract(req, new TableReq());
        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        ListForPage<T> list;
        if (request.getInput() != null && !request.getInput().isBlank()
                && request.getFilter() != null && !request.getFilter().isBlank()) {
            list = methodWithFilters.get(currentAccount, request.getPageNumber(), request.getFilter(), request.getInput());
        } else {
            list = methodWithoutFilters.get(currentAccount, request.getPageNumber());
        }
        System.out.println("list " + list);
        System.out.println("command " + command);
        System.out.println("page " + request.getPage());
        list.setPage(request.getPage());
        list.setCommand(command.name());

        req.setAttribute(LIST_FOR_PAGE, list);

        return MAIN_PAGE_PATH;
    }

}
