package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.fiterForSearch.FilterManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_FILTERS_PAGE_PATH;

public class ShowServiceCenterTable implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<String> modelFilters = FilterManager.getFiltersForServiceCenter();
        Map<String, String> userInput = Extractor.extractParameterMap(req, modelFilters);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(SERVICE_CENTER_FILTERS_PAGE_PATH);
        TablePage<ServiceCenterDTO> serviceCenters = serviceCenterService.findForPage(reqData.getPageNumber(), userInput);
        CommandHelper.insertTableData(req, reqData, serviceCenters);
        return Extractor.extractMainPagePath(req);

    }

}