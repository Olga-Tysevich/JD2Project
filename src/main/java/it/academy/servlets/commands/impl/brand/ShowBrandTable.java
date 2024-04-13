package it.academy.servlets.commands.impl.brand;

import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_BRAND_TABLE;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (b, f, c) -> brandService.findBrands(b, f, c),
                    (i) -> brandService.findBrands(i),
                    SHOW_BRAND_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}