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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.REPAIR_FILTERS_PAGE_PATH;

public class ShowRepairTable implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO account = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = account.getRole();
        TablePageReq reqData = Extractor.extractDataForTable(req, REPAIR_FILTERS_PAGE_PATH);

        TablePage<RepairDTO> table = RoleEnum.ADMIN.equals(role) ?
                repairService.findForPage(reqData.getPageNumber(), reqData.getUserInput())
                : repairService.findForPageByUserId(account.getServiceCenterId(), reqData.getPageNumber(), reqData.getUserInput());

        CommandHelper.insertTableData(req, reqData, table);

        return Extractor.extractMainPagePath(req);

    }

}
