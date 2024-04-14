package it.academy.servlets.commands.impl.model;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ChangeModelDTO modelDTO = ExtractorImpl.extract(req, new ChangeModelDTO());

        try {
            modelService.createModel(modelDTO);
        } catch (IllegalArgumentException | AccessDenied e) {
            System.out.println("error " + e.getMessage());
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowModelTable().execute(req);
    }

}
