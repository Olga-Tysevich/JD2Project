package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class RepairTableExtractor implements Extractor {
    private RepairService repairService = new RepairServiceImpl();
    private RepairStatus lastStatus;

    @Override
    public void extractValues(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        RepairStatus lastStatus = RepairStatus.valueOf(req.getParameter(REPAIR_STATUS));
        ListForPage<RepairDTO> repairs;

        if (RepairStatus.ALL.equals(lastStatus)) {
            repairs = repairService.findRepairs(pageNumber);
        } else {
            pageNumber = lastStatus.equals(this.lastStatus)? pageNumber : FIRST_PAGE;
            repairs = repairService.findRepairsByStatus(lastStatus, pageNumber);
        }

        this.lastStatus = lastStatus;
        req.setAttribute(PAGE, REPAIR_TABLE_PAGE_PATH);
        req.setAttribute(LIST_FOR_PAGE, repairs);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(MAX_PAGE, repairs.getMaxPageNumber());
        req.setAttribute(REPAIR_STATUS, lastStatus);

    }

}
