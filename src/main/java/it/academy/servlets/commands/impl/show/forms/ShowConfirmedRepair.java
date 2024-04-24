package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.ChangeRepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowConfirmedRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        try {
            long repairId = Long.parseLong(req.getParameter(OBJECT_ID));
            ChangeRepairFormDTO changeRepairForm = repairService.findRepair(repairId);
            long brandId = changeRepairForm.getRepairFormDTO().getSelectedBrandId();

            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(CHANGE_REPAIR_FORM, changeRepairForm);

            return CommandHelper.insertFormData(req,
                    REPAIR_TABLE_PAGE_PATH,
                    REPAIR_PAGE_PATH,
                    CHANGE_REPAIR,
                    SHOW_REPAIR_TABLE);
        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
