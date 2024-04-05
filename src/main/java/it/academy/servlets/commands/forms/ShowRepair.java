package it.academy.servlets.commands.forms;

import it.academy.dto.device.ModelDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long modelId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : DEFAULT_ID;
        ModelDTO model = repairService.findModel(modelId);

        req.setAttribute(MODEL, model);

        return REPAIR_PAGE_PATH;

    }
    
}
