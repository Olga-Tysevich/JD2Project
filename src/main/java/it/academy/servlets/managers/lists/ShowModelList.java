package it.academy.servlets.managers.lists;

import it.academy.dto.ModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowModelList implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long brandId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : BRAND_DEFAULT_ID;
        List<ModelDTO> models = modelService.findModelsByBrandId(brandId);
        req.setAttribute(MODELS, models);

        return MODEL_LIST_PAGE_PATH;
    }
}
