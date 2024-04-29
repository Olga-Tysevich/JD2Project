package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowBrandTable extends ShowTable {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        TableReq dataForPage = Extractor.extractDataForTable(req);
        ListForPage<BrandDTO> brands = brandService.findBrands(
                dataForPage.getPageNumber(),
                dataForPage.getFilter(),
                dataForPage.getInput());
        setTableData(req, dataForPage, brands);
        log.info(CURRENT_TABLE, brands);
        return ADMIN_MAIN_PAGE_PATH;
    }

}