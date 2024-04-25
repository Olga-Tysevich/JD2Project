package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.ModelForChangeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowModelTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.ADD_MODEL;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_MODEL_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.MODEL_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.MODEL_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        ChangeModelDTO forCreate = Extractor.extract(req, new ChangeModelDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            modelService.createModel(forCreate);
        } catch (ObjectAlreadyExist e) {
            ModelForChangeDTO model = modelService.getModelForm();
            req.setAttribute(MODEL, model);
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    MODEL_TABLE_PAGE_PATH,
                    MODEL_PAGE_PATH,
                    ADD_MODEL,
                    SHOW_MODEL_TABLE);
        }
        return new ShowModelTable().execute(req, resp);
    }

}
