package it.academy.servlets.commands.impl.update;

import it.academy.dto.spare_part_order.ChangeSparePartOrderDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.tables.GetRepairs;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ChangeSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ChangeSparePartOrderDTO order = Extractor.extractObject(req, new ChangeSparePartOrderDTO());
        sparePartOrderService.update(order);
        return new GetRepairs().execute(req, resp);

    }

}
