package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class RepairTableExtractor implements Extractor {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public void extractValues(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ListForPage<RepairDTO> repairs = repairService.findRepairs(pageNumber);

        req.setAttribute(PAGE, REPAIR_TABLE_PAGE_PATH);
        req.setAttribute(LIST_FOR_PAGE, repairs);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(MAX_PAGE, repairs.getMaxPageNumber());

    }

}
