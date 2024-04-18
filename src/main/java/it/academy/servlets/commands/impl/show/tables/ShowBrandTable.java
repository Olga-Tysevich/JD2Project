package it.academy.servlets.commands.impl.show.tables;

import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_BRAND_TABLE;
import static it.academy.utils.constants.Constants.ERROR_PAGE_PATH;

public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
//            return TableExtractor.extract(req,
//                    (a, b, f, c) -> brandService.findBrands(a, b, f, c),
//                    (a, i) -> brandService.findBrands(a, i),
//                    SHOW_BRAND_TABLE);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}