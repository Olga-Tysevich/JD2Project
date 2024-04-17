package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.resp.SparePartDTO;
import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

public class ShowSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        long sparePartId = Long.parseLong(req.getParameter(OBJECT_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        SparePartDTO sparePart = sparePartService.findSparePart(sparePartId);

        req.setAttribute(SPARE_PART, sparePart);
        req.setAttribute(PAGE, page);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return SPARE_PART_PAGE_PATH;
    }

}
