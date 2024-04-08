package it.academy.servlets.commands.impl.delete;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.services.RepairService;
import it.academy.services.SparePartService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.CHANGE_REPAIR_PAGE_PATH;

public class DeleteSparePartOrder implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));

        System.out.println(orderId);
        System.out.println(repairId);
        sparePartService.removeSparePartOrder(orderId);
//        RepairDTO repair = repairService.findRepair(repairId);
//        List<BrandDTO> brandDTOList = repairService.findBrands();
//        List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(repair.getBrandId());
//        List<SparePartOrderDTO> orders = sparePartService.findSparePartOrdersByRepairId(repair.getId());
//
//        req.setAttribute(REPAIR, repair);
//        req.setAttribute(BRANDS, brandDTOList);
//        req.setAttribute(MODELS, modelDTOList);
//        req.setAttribute(ORDERS, orders);
//        req.setAttribute(BRAND_ID, repair.getBrandId());
//        req.setAttribute(CURRENT_BRAND_ID, repair.getBrandId());

        return String.format(OPEN_REPAIR_PAGE, repairId);
    }
}
