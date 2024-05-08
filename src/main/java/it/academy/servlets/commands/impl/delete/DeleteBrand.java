package it.academy.servlets.commands.impl.delete;

import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long brandId = Extractor.extractLongVal(req, OBJECT_ID, null);
            brandService.delete(brandId);
        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }
        return Extractor.extractLastPage(req);
    }
}
