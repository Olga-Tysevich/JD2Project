package it.academy.servlets.commands.impl.show.forms;

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

public class ShowUpdateRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        RoleEnum role = (RoleEnum) req.getSession().getAttribute(ROLE);
        long repairId = Extractor.extractLongVal(req, OBJECT_ID, DEFAULT_ID);
        RepairStatus status = Extractor.extractRepairStatus(req);

        return RoleEnum.ADMIN.equals(role) || RepairStatus.REQUEST.equals(status) ?
                getRepairForAdmin(req, resp, repairId)
                : getRepairForUser(req, resp, repairId);

    }

    private String getRepairForAdmin(HttpServletRequest req, HttpServletResponse resp, long repairId) {
        RepairFormDTO adminRepairForm = repairService.findRepair(repairId);
        long brandId = adminRepairForm.getRepairDTO().getBrandId();
        req.setAttribute(BRAND_ID, brandId);
        req.setAttribute(REPAIR_FORM, adminRepairForm);
        return new ShowForm(ADMIN_REPAIR_PAGE_PATH).execute(req, resp);
    }

    private String getRepairForUser(HttpServletRequest req, HttpServletResponse resp, long repairId) {
        UserRepairFormDTO userRepairFormDTO = repairService.findRepairForUser(repairId);
        req.setAttribute(REPAIR_FORM, userRepairFormDTO);
        return new ShowForm(USER_REPAIR_PAGE_PATH).execute(req, resp);
    }
}