package it.academy.servlets.commands.impl.update;

import it.academy.dto.spare_part_order.ChangeSparePartOrderDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ChangeSparePartOrderDTO order = Extractor.extractObject(req, new ChangeSparePartOrderDTO());
        sparePartOrderService.update(order);
        return Extractor.extractLastPage(req);

    }

}
