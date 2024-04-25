package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_BRAND_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        ListForPage<BrandDTO> brands;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        brands = brandService.findBrands(
                dataFromPage.getPageNumber(),
                dataFromPage.getFilter(),
                dataFromPage.getInput());
        brands.setPage(dataFromPage.getPage());
        brands.setCommand(SHOW_BRAND_TABLE.name());
        req.setAttribute(LIST_FOR_PAGE, brands);
        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
        log.info(CURRENT_TABLE, brands);
        return ADMIN_MAIN_PAGE_PATH;
    }

}