package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowModelTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

@Slf4j
public class ChangeModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            String result = FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> modelService.updateModel((ChangeModelDTO) a)),
                    (id) -> modelService.findModel((Long) id),
                    ChangeModelDTO.class,
                    MODEL,
                    MODEL_PAGE_PATH,
                    () -> new ShowModelTable().execute(req));
            log.info(String.format(CURRENT_PAGE, result));
            return result;
        } catch (Exception e) {
            return ERROR_PAGE_PATH;
        }
    }

}