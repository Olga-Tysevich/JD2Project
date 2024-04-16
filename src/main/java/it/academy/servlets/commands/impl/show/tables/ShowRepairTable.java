package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.req.TableReq;
import it.academy.dto.resp.ListForPage;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RepairStatus;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowRepairTable implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private RepairStatus lastStatus;

    @Override
    public String execute(HttpServletRequest req) {

        TableReq request = Extractor.extract(req, new TableReq());

        RepairStatus lastStatus = req.getParameter(REPAIR_STATUS) != null ?
                RepairStatus.valueOf(req.getParameter(REPAIR_STATUS)) : RepairStatus.ALL;
        ListForPage<ChangeRepairDTO> repairs;

        int pageNumber = lastStatus.equals(this.lastStatus) ? request.getPageNumber() : FIRST_PAGE;
        if (RepairStatus.ALL.equals(lastStatus)) {
            repairs = repairService.findRepairs(request.getPageNumber());
        } else {
            repairs = repairService.findRepairsByStatus(lastStatus, pageNumber);
        }

        repairs.setCommand(req.getParameter(COMMAND));
        repairs.setPage(req.getParameter(PAGE));
        this.lastStatus = lastStatus;
        req.setAttribute(LIST_FOR_PAGE, repairs);
        req.setAttribute(REPAIR_STATUS, lastStatus);

        return MAIN_PAGE_PATH;

    }

}
