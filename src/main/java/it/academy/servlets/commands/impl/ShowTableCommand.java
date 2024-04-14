package it.academy.servlets.commands.impl;

import it.academy.dto.resp.ListForPage;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;
import it.academy.utils.interfaces.ActiveEntitySupplier;
import it.academy.utils.interfaces.FilteredEntitySupplier;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_MODEL_TABLE;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ShowTableCommand<T> implements ActionCommand {
    private FilteredEntitySupplier<ListForPage<T>> methodWithFilters;
    private ActiveEntitySupplier<ListForPage<T>> methodWithoutFilters;
    private String pagePath;

    public ShowTableCommand(FilteredEntitySupplier<ListForPage<T>> methodWithFilters, ActiveEntitySupplier<ListForPage<T>> methodWithoutFilters, String pagePath) {
        this.methodWithFilters = methodWithFilters;
        this.methodWithoutFilters = methodWithoutFilters;
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, b, f, c) -> methodWithFilters.get(a, b, f, c),
                    (a, i) -> methodWithoutFilters.get(a, i),
                    SHOW_MODEL_TABLE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }
}