package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.TableReq;
import it.academy.dto.resp.ListForPage;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ListForPage<BrandDTO> brands;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        boolean findByFilters = dataFromPage.getFilter() != null && dataFromPage.getInput() != null;

        if (findByFilters) {
            brands = brandService.findBrands(
                    dataFromPage.getPageNumber(),
                    dataFromPage.getFilter(),
                    dataFromPage.getInput());
        } else {
            brands = brandService.findBrands(dataFromPage.getPageNumber());
        }

        brands.setPage(dataFromPage.getPage());
        brands.setCommand(dataFromPage.getCommand());
        req.setAttribute(LIST_FOR_PAGE, brands);
        return MAIN_PAGE_PATH;
    }

}