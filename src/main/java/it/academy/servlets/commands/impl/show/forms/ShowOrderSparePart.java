package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.services.device.SparePartService;
import it.academy.services.device.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class ShowOrderSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        long repairId = Long.parseLong(req.getParameter(OBJECT_ID));
        long deviceTypeId = Long.parseLong(req.getParameter(SPARE_PART_MODEL_ID));
        String repairNumber = req.getParameter(REPAIR_NUMBER);
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        List<SparePartForChangeDTO> spareParts = new ArrayList<>();
//                sparePartService.findSparePartsByDeviceTypeId(currentAccount, deviceTypeId);

        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(OBJECT_ID, repairId);
        req.setAttribute(SPARE_PARTS, spareParts);
        req.setAttribute(REPAIR_NUMBER, repairNumber);
        req.setAttribute(SPARE_PART_MODEL_ID, deviceTypeId);

        return SPARE_PART_ORDER_PAGE_PATH;
    }

}
