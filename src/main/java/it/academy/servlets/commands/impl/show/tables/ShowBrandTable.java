package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBrandTable implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);

        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = StringUtils.defaultIfBlank(reqData.getFilter(), StringUtils.EMPTY);

        TablePage<BrandDTO> brands = StringUtils.isBlank(filter) ?
                brandService.findForPage(reqData.getPageNumber())
                : brandService.findForPageByFilter(reqData.getPageNumber(), reqData.getFilter(), reqData.getInput());

        CommandHelper.insertTableData(req, reqData, brands);
        return Extractor.extractMainPagePath(req);
    }

}