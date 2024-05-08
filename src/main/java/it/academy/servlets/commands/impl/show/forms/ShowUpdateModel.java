package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowUpdateModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        Long id = Extractor.extractLongVal(req, OBJECT_ID, null);
        ModelDTO model = modelService.find(id);
        req.setAttribute(MODEL, model);
        return new ShowModelForm(UPDATE_MODEL_PAGE_PATH).execute(req, resp);

    }

}
