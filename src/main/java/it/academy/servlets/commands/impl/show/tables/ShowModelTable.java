package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.fiterForSearch.FilterManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import static it.academy.utils.constants.JSPConstant.MODEL_FILTERS_PAGE_PATH;

public class ShowModelTable implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<String> modelFilters = FilterManager.getFiltersForModel();
        Map<String, String> userInput = Extractor.extractParameterMap(req, modelFilters);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(MODEL_FILTERS_PAGE_PATH);
        TablePage<ModelDTO> brands = modelService.findForPage(reqData.getPageNumber(), userInput);
        CommandHelper.insertTableData(req, reqData, brands);
        return Extractor.extractMainPagePath(req);
    }

}