package it.academy.servlets.commands.impl.change;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ChangeRepairFormDTO;
import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairFormDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.impl.ExtractorImpl;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeRepair extends AddRepair {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            RepairDTO repairDTO = ExtractorImpl.extract(req, new RepairDTO());
            repairDTO.setCurrentAccount(currentAccount);

            if (checkBrand(req, currentAccount, repairDTO)) {
                RepairFormDTO repairFormDTO = (RepairFormDTO) req.getAttribute(REPAIR_FORM);
                repairDTO.setBrandId(repairFormDTO.getCurrentBrandId());
                ChangeRepairFormDTO changeRepairFormDTO = new ChangeRepairFormDTO(repairDTO, repairFormDTO, null);
                req.setAttribute(CHANGE_REPAIR_FORM, changeRepairFormDTO);
                String page = req.getParameter(PAGE);
                req.setAttribute(PAGE, page);
                return REPAIR_PAGE_PATH;
            }

            repairService.updateRepair(repairDTO);

            return new ShowRepairTable().execute(req);
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }

}
