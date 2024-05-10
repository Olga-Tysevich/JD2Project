package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.SERVICE_CENTER_FILTERS_PAGE_PATH;

public class ShowServiceCenterTable implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req, SERVICE_CENTER_FILTERS_PAGE_PATH);
        TablePage<ServiceCenterDTO> serviceCenters = serviceCenterService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, serviceCenters);
        return Extractor.extractMainPagePath(req);

    }

}