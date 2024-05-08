package it.academy.servlets.commands.impl.delete;

import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteSparePartOrder implements ActionCommand {
    private SparePartOrderService orderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long sparePartId = Extractor.extractLongVal(req, OBJECT_ID, null);
            orderService.delete(sparePartId);
        } catch (Exception e) {
            req.setAttribute(ERROR, ORDER_DELETE_FAILED_MESSAGE);
        }
        return Extractor.extractLastPage(req);
    }
}