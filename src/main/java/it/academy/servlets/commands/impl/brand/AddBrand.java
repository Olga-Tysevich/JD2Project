package it.academy.servlets.commands.impl.brand;

import it.academy.dto.device.req.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Extractor;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        BrandDTO brandDTO = Extractor.extract(req, new BrandDTO());
        try {
            brandService.createBrand(brandDTO);
        } catch (IllegalArgumentException e) {
            System.out.println("error " + e.getMessage());
            req.setAttribute(ERROR, e.getMessage());

            return new ShowBrandTable().execute(req);
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MAIN_PAGE_PATH;
    }

}
