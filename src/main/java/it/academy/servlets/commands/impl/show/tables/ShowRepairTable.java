package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowRepairTable extends ShowTable {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = accountDTO.getRole();

        TableReq dataForPage = Extractor.extractDataForTable(req);
        RepairStatus status = req.getParameter(REPAIR_STATUS) != null && !req.getParameter(REPAIR_STATUS).equals(ALL_REPAIRS) ?
                RepairStatus.valueOf(req.getParameter(REPAIR_STATUS)) : null;

        ListForPage<RepairDTO> repairs = RoleEnum.ADMIN.equals(role) ? findRepairForAdmin(status, dataForPage)
                : findRepairForUser(status, dataForPage, accountDTO.getServiceCenterId());

        setTableData(req, dataForPage, repairs);
        log.info(CURRENT_TABLE, repairs);

        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;

    }

    private ListForPage<RepairDTO> findRepairForAdmin(RepairStatus status, TableReq dataFromPage) {
        return status != null ? repairService.findRepairsByStatus(status, dataFromPage.getPageNumber())
                : repairService.findRepairs(dataFromPage.getPageNumber(), dataFromPage.getFilter(), dataFromPage.getInput());
    }

    private ListForPage<RepairDTO> findRepairForUser(RepairStatus status, TableReq dataFromPage, long serviceCenterId) {
        return status != null ? repairService.findRepairsByStatusForUser(serviceCenterId, status, dataFromPage.getPageNumber())
                : repairService.findRepairsForUser(serviceCenterId, dataFromPage.getPageNumber(), dataFromPage.getFilter(), dataFromPage.getInput());
    }

}
