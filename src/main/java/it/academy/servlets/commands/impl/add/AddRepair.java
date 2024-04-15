package it.academy.servlets.commands.impl.add;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairFormDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

            long lastBrandId = req.getParameter(BRAND_ID) != null ?
                    Long.parseLong(req.getParameter(BRAND_ID)) : DEFAULT_ID;
            long currentBrandId = req.getParameter(CURRENT_BRAND_ID) != null ?
                    Long.parseLong(req.getParameter(CURRENT_BRAND_ID)) : DEFAULT_ID;
            int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                    Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
            String page = req.getParameter(PAGE);
            RepairDTO repairDTO = ExtractorImpl.extract(req, new RepairDTO());
            repairDTO.setCurrentAccount(currentAccount);

            if (!(currentBrandId == lastBrandId)) {
                RepairFormDTO repairForm = repairService.getRepairFormData(currentAccount, currentBrandId);

                req.setAttribute(REPAIR_FORM, repairForm);
                req.setAttribute(BRAND_ID, repairForm.getCurrentBrandId());
                req.setAttribute(REPAIR, repairDTO);
                req.setAttribute(PAGE_NUMBER, pageNumber);
                return ADD_REPAIR_PAGE_PATH;
            }

            req.setAttribute(PAGE_NUMBER, pageNumber);
            req.setAttribute(PAGE, page);

            repairService.addRepair(repairDTO);

            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
