package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePageReq;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class GetSparePartOrders implements ActionCommand {
    private SparePartOrderService orderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq dataFromPage = Extractor.extractObject(req, new TablePageReq());


//        TablePage<SparePartOrderDTO> sparePartOrders =
//
//        sparePartOrders = orderService.findForPageByFilter(
//                dataFromPage.getPageNumber(),
//                dataFromPage.getFilter(),
//                dataFromPage.getInput());
//        sparePartOrders.setPage(SPARE_PART_ORDER_TABLE_PAGE_PATH);
//        sparePartOrders.setCommand(SHOW_SPARE_PART_ORDER_TABLE.name());
//        req.setAttribute(TABLE_PAGE, sparePartOrders);
//        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
//        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
//
//        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
        return null;

    }
}

