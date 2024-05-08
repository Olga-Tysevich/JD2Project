package it.academy.servlets.commands.impl.update;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class UpdateRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        RepairTypeDTO forUpdate = Extractor.extractObject(req, new RepairTypeDTO());

        try {
            repairTypeService.update(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(REPAIR_TYPE, forUpdate);
            req.setAttribute(FORM_PAGE, UPDATE_REPAIR_TYPE_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);

    }

}