package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowUpdateRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        long repairTypeId = Extractor.extractLongVal(req, OBJECT_ID, null);
        RepairTypeDTO repairType = repairTypeService.find(repairTypeId);
        req.setAttribute(REPAIR_TYPE, repairType);
        return new ShowForm(UPDATE_REPAIR_TYPE_PAGE_PATH).execute(req,resp);
    }
}
