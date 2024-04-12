package it.academy.servlets.commands.impl.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.dto.table.req.TableReq;
import it.academy.dto.table.resp.ListForPage;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.factory.CommandEnum.SHOW_SERVICE_CENTER_TABLE;
import static it.academy.utils.Constants.*;

public class ShowServiceCenterTable implements ActionCommand {
   private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        TableReq request = it.academy.utils.Extractor.extract(req, new TableReq());
        System.out.println("show service center table req " + request);

        ListForPage<ServiceCenterDTO> serviceCenters;
        if (request.getInput() != null && !request.getInput().isBlank()
                && request.getFilter() != null && !request.getFilter().isBlank()) {
            serviceCenters = serviceCenterService.findServiceCenter(request.getPageNumber(), request.getFilter(), request.getInput());
        } else {
            serviceCenters = serviceCenterService.findServiceCenter(request.getPageNumber());
        }
        System.out.println("show service center table accounts " + serviceCenters);
        serviceCenters.setPage(SERVICE_CENTER_TABLE_PAGE_PATH);
        serviceCenters.setCommand(SHOW_SERVICE_CENTER_TABLE.name());
        System.out.println("show service centers request after find" + serviceCenters);

        req.setAttribute(LIST_FOR_PAGE, serviceCenters);

        return MAIN_PAGE_PATH;

    }
}