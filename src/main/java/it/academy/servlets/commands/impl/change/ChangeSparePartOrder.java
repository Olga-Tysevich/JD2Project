package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.services.RepairService;
import it.academy.services.SparePartService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ChangeSparePartOrder implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();
    private RepairService repairService = new RepairServiceImpl();
    private Extractor extractor = new RepairExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        String departureDateString = req.getParameter(DEPARTURE_DATE);
        String deliveryDateString = req.getParameter(DELIVERY_DATE);
        System.out.println("dep " + departureDateString);
        System.out.println("del " + deliveryDateString);
        SparePartOrderDTO order = sparePartService.findSparePartOrder(orderId);
        System.out.println(order);

        if (deliveryDateString != null && !deliveryDateString.isEmpty()) {
            order.setDeliveryDate(Date.valueOf(deliveryDateString));
        }

        if (departureDateString != null && !departureDateString.isEmpty()) {
            order.setDepartureDate(Date.valueOf(departureDateString));
        }
        System.out.println(order);

        sparePartService.changeSparePartOrder(order);
        RepairDTO repair = repairService.findRepair(order.getRepairId());
        List<BrandDTO> brandDTOList = repairService.findBrands();
        List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(repair.getBrandId());
        List<SparePartOrderDTO> orders = sparePartService.findSparePartOrdersByRepairId(repair.getId());
        orders.forEach(System.out::println);

        req.setAttribute(REPAIR, repair);
        req.setAttribute(BRANDS, brandDTOList);
        req.setAttribute(MODELS, modelDTOList);
        req.setAttribute(ORDERS, orders);
        req.setAttribute(BRAND_ID, repair.getBrandId());
        req.setAttribute(CURRENT_BRAND_ID, repair.getBrandId());

        return CHANGE_REPAIR_PAGE_PATH;
    }

}
