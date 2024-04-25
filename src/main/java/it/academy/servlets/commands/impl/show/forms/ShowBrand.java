package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.BRAND_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.BRAND_TABLE_PAGE_PATH;

public class ShowBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        long brandId = Long.parseLong(req.getParameter(OBJECT_ID));
        BrandDTO brand = brandService.findBrand(brandId);

        req.setAttribute(BRAND, brand);

        return CommandHelper.insertFormData(req,
                BRAND_TABLE_PAGE_PATH,
                BRAND_PAGE_PATH,
                CHANGE_BRAND,
                SHOW_BRAND_TABLE);
    }

}
