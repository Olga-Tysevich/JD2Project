package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowServiceCenterTable extends ShowTable {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        TableReq dataForPage = Extractor.extractDataForTable(req);
        ListForPage<ServiceCenterDTO> serviceCenters = serviceCenterService.findServiceCenters(
                dataForPage.getPageNumber(),
                dataForPage.getFilter(),
                dataForPage.getInput());
        setTableData(req, dataForPage, serviceCenters);
        log.info(CURRENT_TABLE, serviceCenters);
        return ADMIN_MAIN_PAGE_PATH;

    }

}