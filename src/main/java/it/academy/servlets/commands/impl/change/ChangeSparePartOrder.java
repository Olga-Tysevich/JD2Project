package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeSparePartOrderDTO;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

@Slf4j
public class ChangeSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {

            ChangeSparePartOrderDTO order = Extractor.extract(req, new ChangeSparePartOrderDTO());

            sparePartOrderService.changeSparePartOrder(order);

            return new ShowRepairTable().execute(req);

        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }

}
