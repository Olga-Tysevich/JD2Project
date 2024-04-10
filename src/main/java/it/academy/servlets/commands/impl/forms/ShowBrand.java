package it.academy.servlets.commands.impl.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long brandId = Long.parseLong(req.getParameter(BRAND_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        BrandDTO brand = brandService.findBrand(brandId);
        System.out.println(brand);

        req.setAttribute(BRAND, brand);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return BRAND_PAGE_PATH;
    }

}
