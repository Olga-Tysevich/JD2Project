package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowModelTable;
import it.academy.servlets.extractors.impl.FormExtractor;
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