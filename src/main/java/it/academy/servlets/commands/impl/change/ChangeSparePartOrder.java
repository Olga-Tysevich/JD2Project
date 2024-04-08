package it.academy.servlets.commands.impl.change;

import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.services.RepairService;
import it.academy.services.SparePartService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import static it.academy.utils.Constants.*;

public class ChangeSparePartOrder implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        String departureDateString = req.getParameter(DEPARTURE_DATE);
        String deliveryDateString = req.getParameter(DELIVERY_DATE);
        SparePartOrderDTO order = sparePartService.findSparePartOrder(orderId);

        if (deliveryDateString != null && !deliveryDateString.isEmpty()) {
            order.setDeliveryDate(Date.valueOf(deliveryDateString));
        }

        if (departureDateString != null && !departureDateString.isEmpty()) {
            order.setDepartureDate(Date.valueOf(departureDateString));
        }

        sparePartService.changeSparePartOrder(order);

        return String.format(OPEN_REPAIR_PAGE, order.getRepairId());
    }

}
