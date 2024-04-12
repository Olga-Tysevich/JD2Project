package it.academy.servlets.commands.impl.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.dto.table.req.TableReq;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.account.ShowAccountTable;
import it.academy.utils.Extractor;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class ChangeServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        System.out.println(req.getParameter(IS_ACTIVE));

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ServiceCenterDTO serviceCenterDTO = Extractor.extract(req, new ServiceCenterDTO());
        TableReq request = Extractor.extract(req, new TableReq());
        System.out.println("change service center req " + request);
        System.out.println("change service center center " + serviceCenterDTO);

        try {
            serviceCenterService.updateServiceCenter(serviceCenterDTO);
        } catch (Exception e) {
            System.out.println(String.format(ERROR_PATTERN, e.getMessage(), serviceCenterDTO));

            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(PAGE, req.getParameter(PAGE));
            req.setAttribute(SERVICE_CENTER, serviceCenterService.findServiceCenter(serviceCenterDTO.getId()));
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return SERVICE_CENTER_PAGE_PATH;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowServiceCenterTable().execute(req);
    }

}