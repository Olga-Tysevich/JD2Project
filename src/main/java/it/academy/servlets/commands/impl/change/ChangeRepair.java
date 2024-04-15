package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.req.RepairFormReq;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairFormDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class ChangeRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

            System.out.println("change repair dto in change repair" );
            ChangeRepairDTO changeRepairDTO = ExtractorImpl.extract(req, new ChangeRepairDTO());
            changeRepairDTO.setCurrentAccount(currentAccount);
            System.out.println("change repair dto " + changeRepairDTO);

            long lastBrandId = req.getParameter(BRAND_ID) != null ?
                    Long.parseLong(req.getParameter(BRAND_ID)) : DEFAULT_ID;
            long currentBrandId = req.getParameter(CURRENT_BRAND_ID) != null ?
                    Long.parseLong(req.getParameter(CURRENT_BRAND_ID)) : DEFAULT_ID;
            int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                    Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
            String page = req.getParameter(PAGE);
            System.out.println("change repair page " + page);
            RepairDTO repairDTO = ExtractorImpl.extract(req, new RepairDTO());
            System.out.println("change repair dto  " + repairDTO);
            repairDTO.setCurrentAccount(currentAccount);

            System.out.println("change current brand " + currentBrandId);
            System.out.println("change last brand " + lastBrandId);
            System.out.println("change repair brands equals " + !(currentBrandId == lastBrandId));
            if (!(currentBrandId == lastBrandId)) {
                RepairFormReq repair = RepairFormReq.builder()
                        .currentAccount(currentAccount)
                        .brandId(DEFAULT_ID)
                        .build();
                RepairFormDTO repairForm = repairService.getRepairFormData(repair);

                req.setAttribute(REPAIR_FORM, repairForm);
                req.setAttribute(REPAIR, repairDTO);
                req.setAttribute(PAGE_NUMBER, pageNumber);
                return REPAIR_PAGE_PATH;
            }
            System.out.println("change repair page");

            req.setAttribute(PAGE_NUMBER, pageNumber);
            req.setAttribute(PAGE, page);
            if (repairDTO.getId() == null){
                System.out.println("change repair add");
                repairService.addRepair(repairDTO);
            } else {
                System.out.println("change repair update");
                repairService.updateRepair(repairDTO);
            }
            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
