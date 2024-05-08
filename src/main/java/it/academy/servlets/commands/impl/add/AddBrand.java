package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.BrandDTO;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_BRAND_PAGE_PATH;

public class AddBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        BrandDTO forCreate = Extractor.extractObject(req, new BrandDTO());

        try {
            brandService.create(forCreate);
        } catch (ObjectAlreadyExist | ValidationException e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(OBJECT_NAME, forCreate.getName());
            req.setAttribute(FORM_PAGE, ADD_BRAND_PAGE_PATH);
        }
        return Extractor.extractLastPage(req);
    }

}
