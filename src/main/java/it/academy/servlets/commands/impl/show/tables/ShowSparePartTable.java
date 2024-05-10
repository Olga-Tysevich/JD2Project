package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.fiterForSearch.FilterManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_FILTERS_PAGE_PATH;

public class ShowSparePartTable implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<String> modelFilters = FilterManager.getFiltersForSparePart();
        Map<String, String> userInput = Extractor.extractParameterMap(req, modelFilters);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(SPARE_PART_FILTERS_PAGE_PATH);
        TablePage<SparePartDTO> spareParts = sparePartService.findForPage(reqData.getPageNumber(), userInput);
        CommandHelper.insertTableData(req, reqData, spareParts);
        return Extractor.extractMainPagePath(req);

    }
}

