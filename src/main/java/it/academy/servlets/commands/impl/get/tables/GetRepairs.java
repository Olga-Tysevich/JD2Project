package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage2;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class GetRepairs extends ShowTable {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String userInput = Extractor.extractString(req, USER_INPUT, null);
        if (!StringUtils.isBlank(userInput)) {
            return new GetRepairsByFilter().execute(req, resp);
        }

        AccountDTO account = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = account.getRole();

        TablePageReq reqData = Extractor.extractDataForTable(req);

        TablePage2<RepairDTO> table = RoleEnum.ADMIN.equals(role) ?
                repairService.findRepairs(reqData.getPageNumber())
                : repairService.findRepairsForUser(account.getServiceCenterId(), reqData.getPageNumber());

        CommandHelper.insertTableData(req, reqData, table);
        log.info(CURRENT_TABLE, table);

        return Extractor.extractMainPagePath(req);

    }

}
