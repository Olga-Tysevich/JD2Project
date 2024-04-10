package it.academy.servlets.commands.impl.forms;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairTypeId = Long.parseLong(req.getParameter(REPAIR_TYPE_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        RepairTypeDTO repairType = repairTypeService.findRepairType(repairTypeId);

        req.setAttribute(REPAIR_TYPE, repairType);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return REPAIR_TYPE_PAGE_PATH;
    }
}
