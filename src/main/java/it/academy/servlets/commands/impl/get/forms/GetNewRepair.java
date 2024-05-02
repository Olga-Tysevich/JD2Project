package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.JSPConstant.USER_MAIN_PAGE_PATH;

public class GetNewRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            long serviceCenterId = accountDTO.getServiceCenterId();

            RepairFormDTO repairForm = repairService.getRepairFormData(DEFAULT_ID);
            RepairDTO repairDTO = Builder.buildEmptyRepair(serviceCenterId);
            repairForm.setRepairDTO(repairDTO);
            req.setAttribute(REPAIR_FORM, repairForm);
            req.setAttribute(BRAND_ID, DEFAULT_ID);

            return ADD_REPAIR_PAGE_PATH;

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return USER_MAIN_PAGE_PATH;
        }

    }
}
