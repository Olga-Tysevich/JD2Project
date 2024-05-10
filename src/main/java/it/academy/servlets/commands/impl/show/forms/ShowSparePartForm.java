package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.constants.Constants.FORM_PAGE;
import static it.academy.utils.constants.Constants.MODELS;

public class ShowSparePartForm implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();
    private String pagePath;

    public ShowSparePartForm(String pagePath) {
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        List<ModelDTO> models = modelService.findAll();
        req.setAttribute(MODELS, models);
        req.setAttribute(FORM_PAGE, pagePath);
        return Extractor.extractLastPage(req);
    }

}
