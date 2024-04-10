package it.academy.servlets.commands.impl.forms;

import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.RepairWorkshopServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepairWorkshop  implements ActionCommand {
    private RepairWorkshopService repairWorkshopService = new RepairWorkshopServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        RepairWorkshopDTO repairWorkshop;
        String id = req.getParameter(REPAIR_WORKSHOP_ID);
        if (id != null && !id.isBlank()) {
            long repairWorkshopId = Long.parseLong(req.getParameter(REPAIR_WORKSHOP_ID));
            repairWorkshop = repairWorkshopService.findRepairWorkshop(repairWorkshopId);
            System.out.println(req.getParameter(COMMAND));
            req.setAttribute(COMMAND, CHANGE_REPAIR_WORKSHOP);
        } else  {
            repairWorkshop = RepairWorkshopDTO.builder()
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
            req.setAttribute(COMMAND, ADD_REPAIR_WORKSHOP);
        }

        req.setAttribute(REPAIR_WORKSHOP, repairWorkshop);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return REPAIR_WORKSHOP_PAGE_PATH;
    }

}
