package it.academy.servlets.commands.impl.forms;

import it.academy.dto.req.BrandDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.spare_parts.SparePartOrderDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.repair.RepairService;
import it.academy.services.spare_part.SparePartOrderService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowConfirmedRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        RepairDTO repairDTO = repairService.findRepair(repairId);
        req.setAttribute(REPAIR, repairDTO);

        List<BrandDTO> brandDTOList = repairService.findBrands();
//        long brandId = repairDTO.getDevice().getModel().getBrandId();
//        List<CreateModelDTO> createModelDTOList = repairService.findModelsByBrandId(brandId);

        if (RepairStatus.WAITING_FOR_SPARE_PARTS.equals(repairDTO.getStatus())) {
            List<SparePartOrderDTO> orders = sparePartOrderService.findSparePartOrdersByRepairId(repairId);
            req.setAttribute(ORDERS, orders);
        }

        req.setAttribute(BRANDS, brandDTOList);
//        req.setAttribute(MODELS, createModelDTOList);

        return CHANGE_REPAIR_PAGE_PATH;
    }

}