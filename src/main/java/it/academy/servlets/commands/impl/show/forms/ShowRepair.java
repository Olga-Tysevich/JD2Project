package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.req.RepairFormReq;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            Long lastBrandId = req.getParameter(BRAND_ID) != null? Long.parseLong(req.getParameter(BRAND_ID)) : DEFAULT_ID;
            System.out.println("show repair last brand id " + lastBrandId);
            Long currentBrandId = req.getParameter(CURRENT_BRAND_ID) != null? Long.parseLong(req.getParameter(CURRENT_BRAND_ID)) : DEFAULT_ID;
            System.out.println("show repair current brand id " + currentBrandId);
            int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
            Long repairId = req.getParameter(REPAIR_ID) != null? Long.parseLong(req.getParameter(REPAIR_ID)) : null;
            System.out.println("show repair rep id " +repairId);
            RepairFormReq repair = RepairFormReq.builder()
                    .currentAccount(currentAccount)
                    .brandId(DEFAULT_ID)
                    .repairId(repairId)
                    .build();
            if (!lastBrandId.equals(currentBrandId)) {
                repair.setBrandId(currentBrandId);
            }

            RepairFormDTO repairForm = repairService.getRepairFormData(repair);
            System.out.println("show repair " + repairForm);
            System.out.println("show repair dto" + repairForm.getRepairDTO());
            req.setAttribute(REPAIR, repairForm.getRepairDTO());
            req.setAttribute(BRAND_ID, currentBrandId);
            req.setAttribute(REPAIR_FORM, repairForm);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            req.setAttribute(PAGE, req.getParameter(PAGE));
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
