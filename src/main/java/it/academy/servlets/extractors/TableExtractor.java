package it.academy.servlets.extractors;

import it.academy.dto.req.TableReq;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.interfaces.ActiveEntitySupplier;
import it.academy.utils.interfaces.FilteredEntitySupplier;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
@Slf4j
public class TableExtractor {

    public static <T> String extract(HttpServletRequest req, FilteredEntitySupplier<ListForPage<T>> methodWithFilters,
                                     ActiveEntitySupplier<ListForPage<T>> methodWithoutFilters, CommandEnum command) {

        log.info(String.format(CURRENT_CLASS,  FormExtractor.class.getSimpleName()));
        TableReq request = Extractor.extract(req, new TableReq());
        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        log.info(String.format(CURRENT_ACCOUNT_PATTERN,  currentAccount));

        ListForPage<T> list;
        if (request.getInput() != null && !request.getInput().isBlank()
                && request.getFilter() != null && !request.getFilter().isBlank()) {
            list = methodWithFilters.get(currentAccount, request.getPageNumber(), request.getFilter(), request.getInput());
        } else {
            list = methodWithoutFilters.get(currentAccount, request.getPageNumber());
        }
        log.info(String.format(CURRENT_COMMAND, command));
        list.setPage(request.getPage());
        list.setCommand(command.name());

        req.setAttribute(LIST_FOR_PAGE, list);

        return MAIN_PAGE_PATH;
    }

}
