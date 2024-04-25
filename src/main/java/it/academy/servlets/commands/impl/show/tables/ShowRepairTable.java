package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RepairStatus;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowRepairTable implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ListForPage<RepairDTO> repairs;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        log.info(OBJECT_EXTRACTED_PATTERN, dataFromPage);
        RepairStatus status = req.getParameter(REPAIR_STATUS) != null && !req.getParameter(REPAIR_STATUS).equals(ALL_REPAIRS) ?
                RepairStatus.valueOf(req.getParameter(REPAIR_STATUS)) : null;

        if (status != null) {
            repairs = repairService.findRepairsByStatus(status, dataFromPage.getPageNumber());
        } else {
            repairs = repairService.findRepairs(dataFromPage.getPageNumber(), dataFromPage.getFilter(), dataFromPage.getInput());
        }

        repairs.setPage(REPAIR_TABLE_PAGE_PATH);
        repairs.setCommand(SHOW_REPAIR_TABLE.name());
        log.info(CURRENT_TABLE, repairs);
        req.setAttribute(LIST_FOR_PAGE, repairs);
        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
        return MAIN_PAGE_PATH;

    }

}
