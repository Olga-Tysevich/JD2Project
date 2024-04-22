package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.ModelForChangeDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.MODEL_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.MODEL_TABLE_PAGE_PATH;

public class ShowModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);

        long modelId = Long.parseLong(req.getParameter(OBJECT_ID));
        ModelForChangeDTO model = modelService.getModelForm(modelId);

        req.setAttribute(MODEL, model);
        req.setAttribute(COMMAND, CHANGE_MODEL);

        return CommandHelper.insertFormData(req,
                MODEL_TABLE_PAGE_PATH,
                MODEL_PAGE_PATH,
                CHANGE_MODEL,
                SHOW_MODEL_TABLE);
    }

}
