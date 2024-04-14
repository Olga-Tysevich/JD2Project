package it.academy.servlets.commands.impl.models;

import it.academy.dto.device.req.ChangeModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ChangeModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> modelService.updateModel((ChangeModelDTO) a)),
                    (id) -> modelService.findModel((Long) id),
                    ChangeModelDTO.class,
                    MODEL,
                    MODEL_PAGE_PATH,
                    () -> new ShowModelTable().execute(req));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}