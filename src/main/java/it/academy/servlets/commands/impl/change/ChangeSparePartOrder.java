package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.spare_parts.SparePartOrderDTO;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

import static it.academy.utils.Constants.*;

public class ChangeSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        String departureDateString = req.getParameter(DEPARTURE_DATE);
        String deliveryDateString = req.getParameter(DELIVERY_DATE);
        SparePartOrderDTO order = sparePartOrderService.findSparePartOrder(orderId);

        if (deliveryDateString != null && !deliveryDateString.isEmpty()) {
            order.setDeliveryDate(Date.valueOf(deliveryDateString));
        }

        if (departureDateString != null && !departureDateString.isEmpty()) {
            order.setDepartureDate(Date.valueOf(departureDateString));
        }

        sparePartOrderService.changeSparePartOrder(order);

        return String.format(OPEN_REPAIR_PAGE, order.getRepairId());
    }

}
