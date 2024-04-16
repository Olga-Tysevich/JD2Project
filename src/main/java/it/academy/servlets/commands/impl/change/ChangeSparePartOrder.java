package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeSparePartOrderDTO;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.impl.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;

public class ChangeSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ChangeSparePartOrderDTO order = ExtractorImpl.extract(req, new ChangeSparePartOrderDTO());

        sparePartOrderService.changeSparePartOrder(order);

        return new ShowRepairTable().execute(req);
    }

}
