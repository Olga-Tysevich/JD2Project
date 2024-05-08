package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;

public class ShowServiceCenterTable implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage<ServiceCenterDTO> serviceCenters = StringUtils.isBlank(filter) ?
                serviceCenterService.findForPage(reqData.getPageNumber())
                : serviceCenterService.findForPageByFilter(reqData.getPageNumber(), filter,
                reqData.getInput());

        CommandHelper.insertTableData(req, reqData, serviceCenters);
        return ADMIN_MAIN_PAGE_PATH;

    }

}