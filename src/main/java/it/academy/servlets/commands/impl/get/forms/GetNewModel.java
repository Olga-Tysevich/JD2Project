package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.device.ModelForChangeDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_MODEL;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class GetNewModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        try {
            ModelForChangeDTO model = modelService.getForm();
            req.setAttribute(MODEL, model);
            return CommandHelper.insertFormData(req, MODEL_PAGE_PATH, ADD_MODEL);
        } catch (DeviceTypesNotFound | BrandsNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return ADMIN_MAIN_PAGE_PATH;
        }
    }
}
