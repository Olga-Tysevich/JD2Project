package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.BrandDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowBrandTable;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

@Slf4j
public class AddBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        BrandDTO brandDTO = Extractor.extract(req, new BrandDTO());

        try {
            brandService.createBrand(brandDTO);
        } catch (IllegalArgumentException | AccessDenied e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), brandDTO));
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowBrandTable().execute(req);
    }

}
