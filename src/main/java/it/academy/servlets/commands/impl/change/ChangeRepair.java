package it.academy.servlets.commands.impl.change;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.ChangeRepairFormDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class ChangeRepair extends AddRepair {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            RepairDTO repairDTO = Extractor.extract(req, new RepairDTO());
            repairDTO.setCurrentAccount(currentAccount);

                RepairFormDTO repairFormDTO = (RepairFormDTO) req.getAttribute(REPAIR_FORM);
                repairDTO.setBrandId(repairFormDTO.getCurrentBrandId());
                ChangeRepairFormDTO changeRepairFormDTO = new ChangeRepairFormDTO(repairDTO, repairFormDTO, null);
                req.setAttribute(CHANGE_REPAIR_FORM, changeRepairFormDTO);
                String page = req.getParameter(PAGE);
                req.setAttribute(PAGE, page);
            repairService.updateRepair(repairDTO);

            return new ShowRepairTable().execute(req);
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }

}
