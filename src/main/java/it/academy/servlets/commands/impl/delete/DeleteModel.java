package it.academy.servlets.commands.impl.delete;

import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;

public class DeleteModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long modelId = Extractor.extractLongVal(req, OBJECT_ID, null);
            modelService.delete(modelId);
        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }
        return Extractor.extractLastPage(req);
    }
}
