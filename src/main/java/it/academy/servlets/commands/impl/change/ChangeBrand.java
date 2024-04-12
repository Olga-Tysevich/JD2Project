package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.req.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.BrandExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class ChangeBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();
    private Extractor<BrandDTO> extractor = new BrandExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.extractValues(req);
        BrandDTO brand = extractor.getResult();
        brandService.updateBrand(brand);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}