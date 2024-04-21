package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
            RepairFormDTO repairForm = repairService.getRepairFormData(currentAccount, DEFAULT_ID);
            req.setAttribute(BRAND_ID, DEFAULT_ID);
            req.setAttribute(REPAIR_FORM, repairForm);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            req.setAttribute(PAGE, req.getParameter(PAGE));
            return ADD_REPAIR_PAGE_PATH;
        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return ADD_REPAIR_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
