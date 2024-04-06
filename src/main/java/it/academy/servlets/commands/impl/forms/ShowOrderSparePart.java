package it.academy.servlets.commands.impl.forms;

import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowOrderSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Long repairId = req.getParameter(REPAIR_ID) == null? null : Long.parseLong(req.getParameter(REPAIR_ID));
        String repairNumber = req.getParameter(REPAIR_NUMBER);
        Long deviceTypeId = req.getParameter(DEVICE_TYPE_ID) == null? null : Long.parseLong(req.getParameter(DEVICE_TYPE_ID));

        if (deviceTypeId != null) {
            List<SparePartDTO> spareParts = sparePartService.findSparePartsByDeviceTypeId(deviceTypeId);
            req.setAttribute(REPAIR_ID, repairId);
            req.setAttribute(DEVICE_TYPE_ID, deviceTypeId);
            req.setAttribute(SPARE_PARTS, spareParts);
            req.setAttribute(REPAIR_NUMBER, repairNumber);


            String temp = req.getParameter(ORDERED_SPARE_PARTS);
            req.setAttribute(ORDERED_SPARE_PARTS, temp);
            System.out.println("ShoeOrder ORDERED_SPARE_PARTS: " + temp);
        }

        return SPARE_PART_ORDER_PAGE_PATH;
    }

}
