package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.device.ModelForChangeDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class GetModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        long modelId = Long.parseLong(req.getParameter(OBJECT_ID));
        ModelForChangeDTO model = modelService.getForm(modelId);
        req.setAttribute(MODEL, model);
        req.setAttribute(COMMAND, CHANGE_MODEL);

        return CommandHelper.insertFormData(req, MODEL_PAGE_PATH, CHANGE_MODEL);
    }

}
