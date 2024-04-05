package it.academy.servlets.commands.forms;

import it.academy.dto.repair.RepairDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowConfirmedRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        RepairDTO repairDTO = repairService.findRepair(repairId);
        req.setAttribute(REPAIR, repairDTO);

        return CHANGE_REPAIR_PAGE_PATH;
    }

}
