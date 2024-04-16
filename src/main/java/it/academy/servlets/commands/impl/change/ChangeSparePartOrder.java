package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeSparePartOrderDTO;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowConfirmedRepair;
import it.academy.servlets.extractors.impl.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.ORDER_REPAIR_ID;
import static it.academy.utils.Constants.REPAIR_ID;

public class ChangeSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ChangeSparePartOrderDTO order = ExtractorImpl.extract(req, new ChangeSparePartOrderDTO());

        sparePartOrderService.changeSparePartOrder(order);

        req.setAttribute(REPAIR_ID, req.getParameter(ORDER_REPAIR_ID));
        return new ShowConfirmedRepair().execute(req);
    }

}
