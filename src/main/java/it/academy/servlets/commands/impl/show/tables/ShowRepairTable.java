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
import it.academy.utils.fiterForSearch.FilterManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.REPAIR_FILTERS_PAGE_PATH;

public class ShowRepairTable implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<String> repairFilters = FilterManager.getFiltersForRepair();
        Map<String, String> userInput = Extractor.extractParameterList(req, repairFilters);
        AccountDTO account = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = account.getRole();

        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(REPAIR_FILTERS_PAGE_PATH);

        TablePage<RepairDTO> table = RoleEnum.ADMIN.equals(role) ?
                repairService.findForPage(reqData.getPageNumber(), userInput)
                : repairService.findForPageByUserId(account.getServiceCenterId(), reqData.getPageNumber(), userInput);

        CommandHelper.insertTableData(req, reqData, table);

        return Extractor.extractMainPagePath(req);

    }

}
