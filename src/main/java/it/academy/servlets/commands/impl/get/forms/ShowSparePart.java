package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandEnum command = CommandEnum.valueOf(req.getParameter(COMMAND));
        switch (command) {
            case SHOW_NEW_SPARE_PART: return showNewSparePart(req);
            case SHOW_SPARE_PART: return showSparePart(req);
            default: return ADMIN_MAIN_PAGE_PATH;
        }

    }

    private String showNewSparePart(HttpServletRequest req) {
        SparePartForChangeDTO model = sparePartService.getSparePartForm();
        req.setAttribute(SPARE_PART, model);
        return insertAttributes(req, ADD_SPARE_PART);
    }

    private String showSparePart(HttpServletRequest req) {
        long sparePartId = Long.parseLong(req.getParameter(OBJECT_ID));
        SparePartForChangeDTO sparePart = sparePartService.findSparePart(sparePartId);

        req.setAttribute(SPARE_PART, sparePart);
        return insertAttributes(req, CHANGE_SPARE_PART);
    }

    private String insertAttributes(HttpServletRequest req, CommandEnum command) {
        return CommandHelper.insertFormData(req,
                SPARE_PART_TABLE_PAGE_PATH,
                SPARE_PART_PAGE_PATH,
                command,
                SHOW_SPARE_PART_TABLE);
    }

}
