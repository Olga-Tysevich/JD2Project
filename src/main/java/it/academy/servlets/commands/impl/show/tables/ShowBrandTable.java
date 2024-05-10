package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.COMPONENT_FILTERS_PAGE_PATH;

public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        TablePageReq reqData = Extractor.extractDataForTable(req, COMPONENT_FILTERS_PAGE_PATH);
        TablePage<BrandDTO> brands = brandService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, brands);
        return Extractor.extractMainPagePath(req);
    }

}