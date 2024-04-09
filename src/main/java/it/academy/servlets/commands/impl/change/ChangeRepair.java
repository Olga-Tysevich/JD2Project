package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import static it.academy.utils.Constants.*;

public class ChangeRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
    private Extractor extractor = new RepairExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        RepairDTO repair = (RepairDTO) extractor.getParameter(REPAIR);
        long lastBrandId = (long) extractor.getParameter(BRAND_ID);
        long currentBrandId = (long) extractor.getParameter(CURRENT_BRAND_ID);

        extractor.insertAttributes(req);

        if (RepairStatus.WAITING_FOR_SPARE_PARTS.equals(repair.getStatus())) {
            List<SparePartOrderDTO> orders = sparePartOrderService.findSparePartOrdersByRepairId(repair.getId());
            req.setAttribute(ORDERS, orders);
        }

        if (!(currentBrandId == lastBrandId)) {
            return CHANGE_REPAIR_PAGE_PATH;
        }

        RepairDTO repairDTO = (RepairDTO) extractor.getParameter(REPAIR);

        repairService.updateRepair(repairDTO);

        return MAIN_PAGE_PATH;
    }

}
