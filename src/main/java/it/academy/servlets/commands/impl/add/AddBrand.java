package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.BrandDTO;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.BrandExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();
    private Extractor<BrandDTO> extractor = new BrandExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        BrandDTO brandDTO  = extractor.getResult();
        brandDTO.setIsActive(true);
        brandService.addBrand(brandDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
