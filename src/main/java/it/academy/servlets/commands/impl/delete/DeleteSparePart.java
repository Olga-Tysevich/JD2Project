package it.academy.servlets.commands.impl.delete;

import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowSparePartTable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl() ;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long sparePartId = Long.parseLong(req.getParameter(OBJECT_ID));

        try {
            sparePartService.deleteSparePart(sparePartId);
        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }

        return new ShowSparePartTable().execute(req, resp);
    }

}