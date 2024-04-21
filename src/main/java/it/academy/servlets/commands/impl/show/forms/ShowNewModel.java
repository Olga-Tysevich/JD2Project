package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.resp.ModelForChangeDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_MODEL;
import static it.academy.utils.constants.Constants.*;

public class ShowNewModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        ModelForChangeDTO model = modelService.getModelForm();

        req.setAttribute(MODEL, model);
        req.setAttribute(PAGE, page);
        req.setAttribute(COMMAND, ADD_MODEL);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MODEL_PAGE_PATH;
    }

}