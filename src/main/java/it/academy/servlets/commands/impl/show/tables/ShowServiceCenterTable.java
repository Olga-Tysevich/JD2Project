package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.req.ServiceCenterDTO;
import it.academy.dto.req.TableReq;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_SERVICE_CENTER_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowServiceCenterTable implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        CommandHelper.checkRole(currentAccount);

        ListForPage<ServiceCenterDTO> serviceCenters;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        boolean findByFilters = dataFromPage.getFilter() != null && dataFromPage.getInput() != null;

        if (findByFilters) {
            serviceCenters = serviceCenterService.findServiceCenters(
                    dataFromPage.getPageNumber(),
                    dataFromPage.getFilter(),
                    dataFromPage.getInput());
        } else {
            serviceCenters = serviceCenterService.findServiceCenters(dataFromPage.getPageNumber());
        }

        serviceCenters.setPage(dataFromPage.getPage());
        serviceCenters.setCommand(SHOW_SERVICE_CENTER_TABLE.name());
        log.info(CURRENT_TABLE, serviceCenters);
        req.setAttribute(LIST_FOR_PAGE, serviceCenters);
        return MAIN_PAGE_PATH;

    }

}