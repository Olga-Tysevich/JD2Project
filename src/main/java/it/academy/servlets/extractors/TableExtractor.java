package it.academy.servlets.extractors;

import it.academy.dto.table.req.TableReq;
import it.academy.dto.table.resp.ListForPage;
import it.academy.servlets.factory.CommandEnum;
import it.academy.utils.interfaces.FilteredEntitySupplier;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;
import static it.academy.utils.Constants.*;

@UtilityClass
public class TableExtractor{

    public static<T> String extract(HttpServletRequest req, FilteredEntitySupplier<ListForPage<T>> methodWithFilters,
                                    Function<Integer, ListForPage<T>> methodWithoutFilters, CommandEnum command) {

        TableReq request = it.academy.utils.Extractor.extract(req, new TableReq());
        System.out.println("show table req " + request);

        ListForPage<T> list;
        if (request.getInput() != null && !request.getInput().isBlank()
                && request.getFilter() != null && !request.getFilter().isBlank()) {
            list = methodWithFilters.get(request.getPageNumber(), request.getFilter(), request.getInput());
        } else {
            list = methodWithoutFilters.apply(request.getPageNumber());
        }
        System.out.println("show table accounts " + list);
        list.setPage(request.getPage());
        list.setCommand(command.name());
        System.out.println("show request after find" + list);

        req.setAttribute(LIST_FOR_PAGE, list);

        return MAIN_PAGE_PATH;
    }

}
