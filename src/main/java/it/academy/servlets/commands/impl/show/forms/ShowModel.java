package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.ModelForChangeDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_MODEL;
import static it.academy.utils.constants.Constants.*;

public class ShowModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long modelId = Long.parseLong(req.getParameter(OBJECT_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        ModelForChangeDTO model = modelService.getModelForm(modelId);

        req.setAttribute(MODEL, model);
        req.setAttribute(PAGE, page);
        req.setAttribute(COMMAND, CHANGE_MODEL);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MODEL_PAGE_PATH;
    }

}
