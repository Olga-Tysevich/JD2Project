package it.academy.servlets.commands.impl.delete;

import it.academy.services.SparePartOrderService;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class DeleteSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        long orderId = Long.parseLong(req.getParameter(OBJECT_ID));
        long repairId = Long.parseLong(req.getParameter(OBJECT_ID));

        System.out.println(orderId);
        System.out.println(repairId);
        sparePartOrderService.removeSparePartOrder(orderId);

        return String.format(OPEN_REPAIR_PAGE, repairId);
    }
}
