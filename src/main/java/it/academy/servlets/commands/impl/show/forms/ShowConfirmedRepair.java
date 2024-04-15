package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ChangeRepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowConfirmedRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
            int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
            String page = req.getParameter(PAGE);

            ChangeRepairFormDTO changeRepairForm = repairService.findRepair(currentAccount, repairId);
            long brandId = changeRepairForm.getRepairDTO().getBrandId();

            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(CHANGE_REPAIR_FORM, changeRepairForm);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            req.setAttribute(PAGE, page);
            return REPAIR_PAGE_PATH;
        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
