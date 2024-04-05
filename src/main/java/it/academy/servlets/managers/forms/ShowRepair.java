package it.academy.servlets.managers.forms;

import it.academy.dto.ModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();
    
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long modelId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : DEFAULT_ID;
        ModelDTO model = modelService.findModel(modelId);
        req.setAttribute(MODEL, model);

        return REPAIR_PAGE_PATH;
    }
    
}
