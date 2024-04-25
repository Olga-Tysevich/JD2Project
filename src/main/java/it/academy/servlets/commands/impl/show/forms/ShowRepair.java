package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.ADD_REPAIR;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

@Slf4j
public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            long brandId = Long.parseLong(req.getParameter(SELECTED_BRAND_ID));
            RepairFormDTO repairForm = repairService.getRepairFormData(brandId);
            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(REPAIR_FORM, repairForm);

            return CommandHelper.insertFormData(req,
                    REPAIR_TABLE_PAGE_PATH,
                    ADD_REPAIR_PAGE_PATH,
                    ADD_REPAIR,
                    SHOW_REPAIR_TABLE);

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        }

    }

}
