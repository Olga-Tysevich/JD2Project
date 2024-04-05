package it.academy.servlets.managers.lists;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.BrandListExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.Constants.BRAND_LIST_PAGE_PATH;

public class ShowBrandList implements ActionCommand {
    private Extractor extractor = new BrandListExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        return BRAND_LIST_PAGE_PATH;
    }


}
