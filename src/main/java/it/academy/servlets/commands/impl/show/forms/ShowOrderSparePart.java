package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.SparePartDTO;
import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowOrderSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        long deviceTypeId = Long.parseLong(req.getParameter(DEVICE_TYPE_ID));
        String repairNumber = req.getParameter(REPAIR_NUMBER);
        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) :FIRST_PAGE;

        List<SparePartDTO> spareParts = sparePartService.findSparePartsByDeviceTypeId(currentAccount, deviceTypeId);

        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(REPAIR_ID, repairId);
        req.setAttribute(SPARE_PARTS, spareParts);
        req.setAttribute(REPAIR_NUMBER, repairNumber);
        req.setAttribute(DEVICE_TYPE_ID, deviceTypeId);

        return SPARE_PART_ORDER_PAGE_PATH;
    }

}
