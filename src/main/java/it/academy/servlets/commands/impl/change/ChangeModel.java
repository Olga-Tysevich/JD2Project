package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowModel;
import it.academy.servlets.commands.impl.show.tables.ShowModelTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);
        ChangeModelDTO forUpdate = Extractor.extract(req, new ChangeModelDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            modelService.updateModel(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return new ShowModel().execute(req);
        }
        return new ShowModelTable().execute(req);

    }

}