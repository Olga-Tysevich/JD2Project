package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class ShowRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long repairTypeId = Long.parseLong(req.getParameter(OBJECT_ID));
        String tablePage = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        RepairTypeDTO repairType = repairTypeService.findRepairType(repairTypeId);

        req.setAttribute(REPAIR_TYPE, repairType);
        req.setAttribute(PAGE, tablePage);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return REPAIR_TYPE_PAGE_PATH;
    }
}
