package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.ModelFormDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.FORM_PAGE;
import static it.academy.utils.constants.JSPConstant.MODEL_FORM;

public class ShowModelForm implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();
    private String pagePath;

    public ShowModelForm(String pagePath) {
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        ModelFormDTO modelForm = modelService.getForm();
        req.setAttribute(MODEL_FORM, modelForm);
        req.setAttribute(FORM_PAGE, pagePath);
        return Extractor.extractLastPage(req);

    }

}
