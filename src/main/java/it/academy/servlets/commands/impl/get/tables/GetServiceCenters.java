package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage2;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class GetServiceCenters extends ShowTable {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage2<ServiceCenterDTO> serviceCenters = StringUtils.isBlank(filter) ?
                serviceCenterService.findForPage(reqData.getPageNumber())
                : serviceCenterService.findForPageByFilter(reqData.getPageNumber(), filter,
                reqData.getInput());

        CommandHelper.insertTableData(req, reqData, serviceCenters);
        log.info(CURRENT_TABLE, serviceCenters);
        return ADMIN_MAIN_PAGE_PATH;

    }

}