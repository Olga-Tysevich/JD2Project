package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowNewModel;
import it.academy.servlets.commands.impl.show.tables.ShowModelTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        CommandHelper.checkRole(accountDTO);

        ChangeModelDTO forCreate = Extractor.extract(req, new ChangeModelDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            modelService.createModel(forCreate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return new ShowNewModel().execute(req);
        }
        return new ShowModelTable().execute(req);
    }

}
