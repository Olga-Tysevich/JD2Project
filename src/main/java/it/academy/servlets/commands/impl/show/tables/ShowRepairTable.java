package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
        RepairStatus status = Extractor.extractRepairStatus(req);

        ListForPage<RepairDTO> repairs = RoleEnum.ADMIN.equals(role) ? findRepairForAdmin(status, dataForPage)
                : findRepairForUser(status, dataForPage, accountDTO.getServiceCenterId());

        CommandHelper.setTableData(req, dataForPage, repairs);
        log.info(CURRENT_TABLE, repairs);

        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;

    }

    private ListForPage<RepairDTO> findRepairForAdmin(RepairStatus status, TableReq dataFromPage) {

        if (status != null) {
            return findRepairsByStatusForAdmin(status, dataFromPage.getPageNumber());
        }

        if (!StringUtils.isBlank(dataFromPage.getInput())) {
            return findRepairsByFilterForAdmin(dataFromPage.getFilter(),
                    dataFromPage.getInput(), dataFromPage.getPageNumber());
        }

        return findRepairsForAdmin(dataFromPage.getPageNumber());
    }

    private ListForPage<RepairDTO> findRepairsForAdmin(int pageNumber) {
        return repairService.findRepairs(pageNumber);
    }

    private ListForPage<RepairDTO> findRepairsByStatusForAdmin(RepairStatus status, int pageNumber) {
        return repairService.findRepairsByStatus(status, pageNumber);
    }

    private ListForPage<RepairDTO> findRepairsByFilterForAdmin(String filter, String input, int pageNumber) {
        return repairService.findRepairsByFilter(pageNumber, filter, input);
    }

    private ListForPage<RepairDTO> findRepairForUser(RepairStatus status, TableReq dataFromPage, long serviceCenterId) {
        if (status != null) {
            return findRepairsByStatusForUser(serviceCenterId, status, dataFromPage.getPageNumber());
        }

        if (!StringUtils.isBlank(dataFromPage.getInput())) {
            return findRepairsByFilterForUser(serviceCenterId,dataFromPage.getFilter(),
                    dataFromPage.getInput(), dataFromPage.getPageNumber());
        }

        return findRepairsForUser(serviceCenterId, dataFromPage.getPageNumber());
    }

    private ListForPage<RepairDTO> findRepairsForUser(long serviceCenterId, int pageNumber) {
        return repairService.findRepairsForUser(serviceCenterId, pageNumber);
    }

    private ListForPage<RepairDTO> findRepairsByStatusForUser(long serviceCenterId, RepairStatus status, int pageNumber) {
        return repairService.findRepairsByStatusForUser(serviceCenterId, status, pageNumber);
    }

    private ListForPage<RepairDTO> findRepairsByFilterForUser(long serviceCenterId, String filter, String input, int pageNumber) {
        return repairService.findRepairsByFilterForUser(serviceCenterId, pageNumber, filter, input);
    }

}
