package it.academy.servlets.commands.impl.change;

import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.services.spare_part.SparePartOrderService;
import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
