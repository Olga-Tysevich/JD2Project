package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.BrandDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowBrandTable;
import it.academy.servlets.extractors.impl.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        BrandDTO brandDTO = ExtractorImpl.extract(req, new BrandDTO());
        try {
            brandService.createBrand(brandDTO);
        } catch (IllegalArgumentException | AccessDenied e) {
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowBrandTable().execute(req);
    }

}
