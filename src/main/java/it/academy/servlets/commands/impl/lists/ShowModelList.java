package it.academy.servlets.commands.impl.lists;

import it.academy.dto.device.ModelDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowModelList implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long brandId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : DEFAULT_ID;
        List<ModelDTO> models = repairService.findModelsByBrandId(brandId);

        req.setAttribute(MODELS, models);

        return MODEL_LIST_PAGE_PATH;
    }
}
