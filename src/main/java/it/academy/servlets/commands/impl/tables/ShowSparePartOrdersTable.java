package it.academy.servlets.commands.impl.tables;

import it.academy.dto.resp.ListForPage;
import it.academy.dto.repair.spare_parts.SparePartOrderDTO;
import it.academy.services.spare_part.SparePartOrderService;
import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowSparePartOrdersTable implements ActionCommand {
    private SparePartOrderService service = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<SparePartOrderDTO> orders;
        if (input != null && !input.isBlank()) {
            orders = service.findSparePartOrders(pageNumber, filter, input);
        } else {
            orders = service.findSparePartOrders(pageNumber);
        }

//        PageManager.insertAttributesForTable(req, orders, SPARE_PART_ORDERS_TABLE_PAGE_PATH);
        req.setAttribute(SHOW_COMMAND, SHOW_SPARE_PART_ORDERS_TABLE);

        return MAIN_PAGE_PATH;
    }

}
