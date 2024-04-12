package it.academy.servlets.commands.impl.forms;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ServiceCenterDTO repairWorkshop;
        String id = req.getParameter(OBJECT_ID);

        if (id != null && !id.isBlank()) {
            long repairWorkshopId = Long.parseLong(req.getParameter(OBJECT_ID));
            repairWorkshop = serviceCenterService.findServiceCenter(repairWorkshopId);
            System.out.println(req.getParameter(COMMAND));
            req.setAttribute(COMMAND, CHANGE_SERVICE_CENTER);
        } else  {
            repairWorkshop = ServiceCenterDTO.builder()
                    .serviceName(DEFAULT_VALUE)
                    .bankAccount(DEFAULT_VALUE)
                    .bankCode(DEFAULT_VALUE)
                    .bankName(DEFAULT_VALUE)
                    .bankAddress(DEFAULT_VALUE)
                    .fullName(DEFAULT_VALUE)
                    .actualAddress(DEFAULT_VALUE)
                    .legalAddress(DEFAULT_VALUE)
                    .phone(DEFAULT_VALUE)
                    .email(DEFAULT_VALUE)
                    .taxpayerNumber(DEFAULT_VALUE)
                    .registrationNumber(DEFAULT_VALUE)
                    .isActive(true)
                    .build();
            req.setAttribute(COMMAND, ADD_SERVICE_CENTER);
        }

        req.setAttribute(SERVICE_CENTER, repairWorkshop);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SERVICE_CENTER_PAGE_PATH;
    }

}
