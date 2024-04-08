package it.academy.servlets.commands.impl.delete;

import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.Constants.*;

public class DeleteSparePartOrder implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));

        System.out.println(orderId);
        System.out.println(repairId);
        sparePartService.removeSparePartOrder(orderId);

        return String.format(OPEN_REPAIR_PAGE, repairId);
    }
}
