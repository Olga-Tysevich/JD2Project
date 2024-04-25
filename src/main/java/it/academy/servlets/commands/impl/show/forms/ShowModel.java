package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.ModelForChangeDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        CommandEnum command = CommandEnum.valueOf(req.getParameter(COMMAND));
        switch (command) {
            case SHOW_NEW_MODEL:
                return showNewModel(req);
            case SHOW_MODEL:
                return showModel(req);
            default:
                return ADMIN_MAIN_PAGE_PATH;
        }
    }

    private String showNewModel(HttpServletRequest req) {
        try {
            ModelForChangeDTO model = modelService.getModelForm();
            req.setAttribute(MODEL, model);
            req.setAttribute(COMMAND, ADD_MODEL);
            return insertAttributes(req, ADD_MODEL);
        } catch (DeviceTypesNotFound | BrandsNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return ADMIN_MAIN_PAGE_PATH;
        }
    }

    private String showModel(HttpServletRequest req) {
        long modelId = Long.parseLong(req.getParameter(OBJECT_ID));
        ModelForChangeDTO model = modelService.getModelForm(modelId);

        req.setAttribute(MODEL, model);
        req.setAttribute(COMMAND, CHANGE_MODEL);

        return insertAttributes(req, CHANGE_MODEL);
    }

    private String insertAttributes(HttpServletRequest req, CommandEnum command) {
        return CommandHelper.insertFormData(req,
                MODEL_TABLE_PAGE_PATH,
                MODEL_PAGE_PATH,
                command,
                SHOW_MODEL_TABLE);
    }

}
