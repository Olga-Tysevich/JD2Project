package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairForTableDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RepairStatus;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowRepairTable implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ListForPage<RepairForTableDTO> repairs;
        TableReq pageData = Extractor.extract(req, new TableReq());
        log.info(OBJECT_EXTRACTED_PATTERN, pageData);
        boolean findByFilters = pageData.getFilter() != null && pageData.getInput() != null;
        RepairStatus status = req.getParameter(REPAIR_STATUS) != null ?
                RepairStatus.valueOf(req.getParameter(REPAIR_STATUS)) : null;

        if (findByFilters) {
            repairs = repairService.findRepairs(
                    pageData.getPageNumber(),
                    pageData.getFilter(),
                    pageData.getInput());
        } else if (status != null) {
            repairs = repairService.findRepairsByStatus(
                    status,
                    pageData.getPageNumber()
            );
        } else {
            repairs = repairService.findRepairs(pageData.getPageNumber());
        }

        repairs.setPage(REPAIR_TABLE_PAGE_PATH);
        repairs.setCommand(SHOW_REPAIR_TABLE.name());
        log.info(CURRENT_TABLE, repairs);
        req.setAttribute(LIST_FOR_PAGE, repairs);
        return MAIN_PAGE_PATH;

    }

}
