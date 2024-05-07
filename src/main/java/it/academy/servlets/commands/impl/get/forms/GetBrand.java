package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.BRAND_PAGE_PATH;

public class GetBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        Long brandId = Extractor.extractLongVal(req, OBJECT_ID, null);
        BrandDTO brand = brandService.find(brandId);
        req.setAttribute(BRAND, brand);
        req.setAttribute(FORM_PAGE, BRAND_PAGE_PATH);
        return Extractor.extractLastPage(req);

    }

}
