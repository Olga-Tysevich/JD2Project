package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.resp.ModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;

public class ShowModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();


    @Override
    public String execute(HttpServletRequest req) {

        long modelId = Long.parseLong(req.getParameter(OBJECT_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        ModelDTO model = modelService.findModel(modelId);
        System.out.println("show model " + model);

        req.setAttribute(MODEL, model);
        req.setAttribute(PAGE, page);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MODEL_PAGE_PATH;
    }

}
