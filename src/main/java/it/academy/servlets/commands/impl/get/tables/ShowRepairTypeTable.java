package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.USER_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowRepairTypeTable extends ShowTable {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        TablePageReq dataForPage = Extractor.extractDataForTable(req);
        TablePage<RepairTypeDTO> repairTypes = repairTypeService.findRepairTypes(
                dataForPage.getPageNumber(),
                dataForPage.getFilter(),
                dataForPage.getInput());
        setTableData(req, dataForPage, repairTypes);
        log.info(CURRENT_TABLE, repairTypes);

        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
    }

}