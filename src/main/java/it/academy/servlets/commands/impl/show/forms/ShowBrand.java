package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class ShowBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();


    @Override
    public String execute(HttpServletRequest req) {

        long brandId = Long.parseLong(req.getParameter(OBJECT_ID));
        String tablePage = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        BrandDTO brand = brandService.findBrand(brandId);

        req.setAttribute(BRAND, brand);
        req.setAttribute(PAGE, tablePage);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return BRAND_PAGE_PATH;
    }

}
