package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_FILTERS_PAGE_PATH;

public class ShowSparePartTable implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req, SPARE_PART_FILTERS_PAGE_PATH);
        TablePage<SparePartDTO> spareParts = sparePartService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, spareParts);
        return Extractor.extractMainPagePath(req);

    }
}

