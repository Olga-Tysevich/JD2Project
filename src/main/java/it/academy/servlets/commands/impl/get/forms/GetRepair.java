package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.repair.RepairFormDTO;
import it.academy.dto.repair.UserRepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class GetRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        RoleEnum role = (RoleEnum) req.getSession().getAttribute(ROLE);
        long repairId = Extractor.extractLongVal(req, OBJECT_ID, DEFAULT_ID);
        RepairStatus status = Extractor.extractRepairStatus(req);

        return RoleEnum.ADMIN.equals(role) || RepairStatus.REQUEST.equals(status) ?
                getRepairForAdmin(req, repairId)
                : getRepairForUser(req, repairId);

    }

    private String getRepairForAdmin(HttpServletRequest req, long repairId) {
        RepairFormDTO changeRepairForm = repairService.findRepair(repairId);
        long brandId = changeRepairForm.getRepairDTO().getBrandId();

        req.setAttribute(BRAND_ID, brandId);
        req.setAttribute(REPAIR_FORM, changeRepairForm);
        return REPAIR_PAGE_PATH;
    }

    private String getRepairForUser(HttpServletRequest req, long repairId) {
        UserRepairFormDTO userRepairFormDTO = repairService.findRepairForUser(repairId);
        req.setAttribute(REPAIR_FORM, userRepairFormDTO);
        return USER_REPAIR_PAGE_PATH;
    }
}