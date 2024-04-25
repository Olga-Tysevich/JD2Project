package it.academy.servlets.commands.impl.delete;

import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long orderId = Long.parseLong(req.getParameter(OBJECT_ID));
        long repairId = Long.parseLong(req.getParameter(OBJECT_ID));

        System.out.println(orderId);
        System.out.println(repairId);
        sparePartOrderService.removeSparePartOrder(orderId);

        return String.format(OPEN_REPAIR_PAGE, repairId);
    }
}
