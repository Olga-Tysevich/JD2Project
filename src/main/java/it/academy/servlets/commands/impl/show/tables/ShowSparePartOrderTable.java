package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part_order.SparePartOrderDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_ORDER_FILTERS_PAGE_PATH;


public class ShowSparePartOrderTable implements ActionCommand {
    private SparePartOrderService orderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req, SPARE_PART_ORDER_FILTERS_PAGE_PATH);
        TablePage<SparePartOrderDTO> orders = orderService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, orders);
        return Extractor.extractMainPagePath(req);

    }
}

