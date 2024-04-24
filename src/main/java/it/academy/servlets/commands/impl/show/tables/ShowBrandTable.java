package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_BRAND_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ListForPage<BrandDTO> brands;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        brands = brandService.findBrands(
                dataFromPage.getPageNumber(),
                dataFromPage.getFilter(),
                dataFromPage.getInput());
        brands.setPage(dataFromPage.getPage());
        brands.setCommand(SHOW_BRAND_TABLE.name());
        log.info(CURRENT_TABLE, brands);
        req.setAttribute(LIST_FOR_PAGE, brands);
        return MAIN_PAGE_PATH;
    }

}