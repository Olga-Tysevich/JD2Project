package it.academy.servlets.commands.impl.add;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairFormDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {

            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

            log.info(String.format(CURRENT_ACCOUNT_PATTERN, currentAccount));

            RepairDTO repairDTO = Extractor.extract(req, new RepairDTO());
            repairDTO.setCurrentAccount(currentAccount);
            String page = req.getParameter(PAGE);

            if (checkBrand(req, currentAccount, repairDTO)) {
                return ADD_REPAIR_PAGE_PATH;
            }

            repairService.addRepair(repairDTO);
            req.setAttribute(PAGE, page);

            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

    protected boolean checkBrand(HttpServletRequest req, AccountDTO currentAccount, RepairDTO repairDTO) {

        long lastBrandId = req.getParameter(BRAND_ID) != null ?
                Long.parseLong(req.getParameter(BRAND_ID)) : DEFAULT_ID;

        long currentBrandId = req.getParameter(CURRENT_BRAND_ID) != null ?
                Long.parseLong(req.getParameter(CURRENT_BRAND_ID)) : DEFAULT_ID;

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        repairDTO.setCurrentAccount(currentAccount);
        if (!(currentBrandId == lastBrandId)) {
            RepairFormDTO repairForm = repairService.getRepairFormData(currentAccount, currentBrandId);
            req.setAttribute(REPAIR_FORM, repairForm);
            req.setAttribute(BRAND_ID, repairForm.getCurrentBrandId());
            req.setAttribute(REPAIR, repairDTO);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return true;
        }

        req.setAttribute(PAGE_NUMBER, pageNumber);
        return false;
    }

}
