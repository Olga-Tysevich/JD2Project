package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_ORDER_PAGE_PATH;

public class ShowSparePartOrder implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairId = Long.parseLong(req.getParameter(OBJECT_ID));
        String repairNumber = req.getParameter(REPAIR_NUMBER);
        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        List<SparePartDTO> spareParts = sparePartService.findSparePartsByRepairId(repairId);
        req.setAttribute(OBJECT_ID, repairId);
        req.setAttribute(SPARE_PARTS, spareParts);
        req.setAttribute(REPAIR_NUMBER, repairNumber);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SPARE_PART_ORDER_PAGE_PATH;
    }

}
