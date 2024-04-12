package it.academy.servlets.commands.impl.brand;

import it.academy.dto.device.req.BrandDTO;
import it.academy.dto.table.req.TableReq;
import it.academy.dto.table.resp.ListForPage;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.factory.CommandEnum.SHOW_BRAND_TABLE;
import static it.academy.utils.Constants.*;

public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        TableReq request = it.academy.utils.Extractor.extract(req, new TableReq());
        System.out.println("show brand table req " + request);

        ListForPage<BrandDTO> brands;
        if (request.getInput() != null && !request.getInput().isBlank()
                && request.getFilter() != null && !request.getFilter().isBlank()) {
            brands = brandService.findBrands(request.getPageNumber(), request.getFilter(), request.getInput());
        } else {
            brands = brandService.findBrands(request.getPageNumber());
        }
        System.out.println("show brand table accounts " + brands);
        brands.setPage(BRAND_TABLE_PAGE_PATH);
        brands.setCommand(SHOW_BRAND_TABLE.name());
        System.out.println("show brand request after find" + brands);

        req.setAttribute(LIST_FOR_PAGE, brands);

        return MAIN_PAGE_PATH;
    }

}