package it.academy.servlets.commands.impl.change;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.JSPConstant.USER_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class ChangeRepair extends AddRepair {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = accountDTO.getRole();

        RepairDTO forUpdate = Extractor.extract(req, new RepairDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        repairService.updateRepair(forUpdate);
        return RoleEnum.ADMIN.equals(role) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;

    }

}
