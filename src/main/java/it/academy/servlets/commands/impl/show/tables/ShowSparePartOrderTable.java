package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part_order.SparePartOrderDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowSparePartOrderTable implements ActionCommand {
    private SparePartOrderService orderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {


        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = StringUtils.defaultIfBlank(reqData.getFilter(), StringUtils.EMPTY);

        TablePage<SparePartOrderDTO> orders = StringUtils.isBlank(filter) ?
                orderService.findForPage(reqData.getPageNumber())
                : orderService.findForPageByFilter(reqData.getPageNumber(), reqData.getFilter(), reqData.getInput());

        CommandHelper.insertTableData(req, reqData, orders);
        log.info(CURRENT_TABLE, orders);
        return Extractor.extractMainPagePath(req);

    }
}

