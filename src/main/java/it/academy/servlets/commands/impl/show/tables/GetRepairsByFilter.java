package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.ACCOUNT;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class GetRepairsByFilter implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO account = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = account.getRole();

        TablePageReq tableReq = Extractor.extractDataForTable(req);

        TablePage<RepairDTO> table = RoleEnum.ADMIN.equals(role) ?
                repairService.findRepairsByFilter(tableReq.getPageNumber(), tableReq.getFilter(), tableReq.getInput())
                : repairService.findRepairsByFilterForUser(account.getServiceCenterId(),
                tableReq.getPageNumber(), tableReq.getFilter(), tableReq.getInput());

        CommandHelper.insertTableData(req, tableReq, table);
        log.info(CURRENT_TABLE, table);

        return Extractor.extractMainPagePath(req);
    }
}