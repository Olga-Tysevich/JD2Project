package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_TABLE_PAGE_PATH;

public class ShowSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long sparePartId = Long.parseLong(req.getParameter(OBJECT_ID));
        SparePartForChangeDTO sparePart = sparePartService.findSparePart(sparePartId);

        req.setAttribute(SPARE_PART, sparePart);
        return CommandHelper.insertFormData(req,
                SPARE_PART_TABLE_PAGE_PATH,
                SPARE_PART_PAGE_PATH,
                CHANGE_SPARE_PART,
                SHOW_SPARE_PART_TABLE);

    }

}
