package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.services.device.SparePartService;
import it.academy.services.device.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_SPARE_PART;
import static it.academy.utils.constants.Constants.*;

public class ShowSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long sparePartId = Long.parseLong(req.getParameter(OBJECT_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        SparePartForChangeDTO sparePart = sparePartService.findSparePart(sparePartId);

        req.setAttribute(SPARE_PART, sparePart);
        req.setAttribute(PAGE, page);
        req.setAttribute(COMMAND, CHANGE_SPARE_PART);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SPARE_PART_PAGE_PATH;

    }

}
