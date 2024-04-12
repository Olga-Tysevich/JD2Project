package it.academy.servlets.commands.impl.brand;

import it.academy.dto.device.req.BrandDTO;
import it.academy.dto.table.req.TableReq;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        BrandDTO brand = Extractor.extract(req, new BrandDTO());
        TableReq request = Extractor.extract(req, new TableReq());
        System.out.println("change brand req " + request);
        System.out.println("change brand " + brand);
        System.out.println("change brand page" + pageNumber);

        try {
            brandService.updateBrand(brand);
        } catch (IllegalArgumentException e) {
            System.out.println("error " + e.getMessage());

            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(PAGE, request.getPage());
            req.setAttribute(BRAND, brandService.findBrand(brand.getId()));
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return BRAND_PAGE_PATH;
        }

        req.setAttribute(PAGE, request.getPage());
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowBrandTable().execute(req);
    }

}