package it.academy.servlets.commands.impl.brand;

import it.academy.dto.req.BrandDTO;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();


    @Override
    public String execute(HttpServletRequest req) {

        long brandId = Long.parseLong(req.getParameter(BRAND_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        BrandDTO brand = brandService.findBrand(brandId);

        req.setAttribute(BRAND, brand);
        req.setAttribute(PAGE, page);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return BRAND_PAGE_PATH;
    }

}
