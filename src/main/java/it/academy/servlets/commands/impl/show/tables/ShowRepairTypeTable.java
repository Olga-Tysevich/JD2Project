package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.ListForPage;
import it.academy.dto.TableReq;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TYPE_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.USER_INPUT;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.USER_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowRepairTypeTable implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        ListForPage<RepairTypeDTO> repairTypes;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        repairTypes = repairTypeService.findRepairTypes(
                dataFromPage.getPageNumber(),
                dataFromPage.getFilter(),
                dataFromPage.getInput());
        repairTypes.setPage(dataFromPage.getPage());
        repairTypes.setCommand(SHOW_REPAIR_TYPE_TABLE.name());
        log.info(CURRENT_TABLE, repairTypes);
        req.setAttribute(LIST_FOR_PAGE, repairTypes);
        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
    }

}