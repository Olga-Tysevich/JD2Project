//package it.academy.servlets.commands.impl.add;
//
//import it.academy.dto.req.BrandDTO;
//import it.academy.dto.device.ModelDTO;
//import it.academy.dto.repair.RepairDTO;
//import it.academy.dto.repair.spare_parts.SparePartOrderDTO;
//import it.academy.services.repair.RepairService;
//import it.academy.services.spare_part.SparePartOrderService;
//import it.academy.services.repair.impl.RepairServiceImpl;
//import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
//import it.academy.servlets.commands.impl.forms.ShowOrderSparePart;
//import it.academy.servlets.extractors.Extractor;
//import it.academy.servlets.extractors.impl.SparePartOrderExtractor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.util.List;
//
//import static it.academy.utils.Constants.*;
//
//public class AddSparePartOrder extends ShowOrderSparePart {
//    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
//    private RepairService repairService = new RepairServiceImpl();
//    private Extractor extractor = new SparePartOrderExtractor();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//
//        extractor.extractValues(req);
//
//        SparePartOrderDTO sparePartOrderDTO = (SparePartOrderDTO) extractor.getParameter(SPARE_PART_ORDER);
//        sparePartOrderService.addSparePartOrder(sparePartOrderDTO);
//
//        long repairId = (long) extractor.getParameter(REPAIR_ID);
//        RepairDTO repairDTO = repairService.findRepair(repairId);
//        long brandId = repairDTO.getDevice().getModel().getBrandId();
//        List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(brandId);
//        List<BrandDTO> brandDTOList = repairService.findBrands();
//        List<SparePartOrderDTO> orders = sparePartOrderService.findSparePartOrdersByRepairId(repairId);
//
//        req.setAttribute(REPAIR, repairDTO);
//        req.setAttribute(BRANDS, brandDTOList);
//        req.setAttribute(MODELS, modelDTOList);
//        req.setAttribute(BRAND_ID, brandId);
//        req.setAttribute(CURRENT_BRAND_ID, brandId);
//        req.setAttribute(ORDERS, orders);
//
//        return CHANGE_REPAIR_PAGE_PATH;
//    }
//
//}
