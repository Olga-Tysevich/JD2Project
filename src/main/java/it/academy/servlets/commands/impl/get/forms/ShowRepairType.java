package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.REPAIR_TYPE_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.REPAIR_TYPE_TABLE_PAGE_PATH;

public class ShowRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairTypeId = Long.parseLong(req.getParameter(OBJECT_ID));
        RepairTypeDTO repairType = repairTypeService.find(repairTypeId);

        req.setAttribute(REPAIR_TYPE, repairType);
        return CommandHelper.insertFormData(req,
                REPAIR_TYPE_TABLE_PAGE_PATH,
                REPAIR_TYPE_PAGE_PATH,
                CHANGE_REPAIR_TYPE,
                GET_REPAIR_TYPE_TABLE);
    }
}
