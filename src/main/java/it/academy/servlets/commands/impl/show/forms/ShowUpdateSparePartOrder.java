package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.spare_part_order.SparePartOrderDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.OBJECT_ID;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowUpdateSparePartOrder implements ActionCommand {
    private SparePartOrderService orderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        Long orderId = Extractor.extractLongVal(req, OBJECT_ID, null);
        SparePartOrderDTO order = orderService.find(orderId);
        req.setAttribute(ORDER, order);
        return new ShowForm(UPDATE_SPARE_PART_ORDER_PAGE_PATH).execute(req,resp);

    }

}
