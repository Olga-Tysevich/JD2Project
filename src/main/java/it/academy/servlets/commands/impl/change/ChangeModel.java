package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.ChangeModelDTO;
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
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_MODEL;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_MODEL_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.MODEL_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.MODEL_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        ChangeModelDTO forUpdate = Extractor.extract(req, new ChangeModelDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            modelService.updateModel(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    MODEL_TABLE_PAGE_PATH,
                    MODEL_PAGE_PATH,
                    CHANGE_MODEL,
                    SHOW_MODEL_TABLE);
        }
        return new ShowModelTable().execute(req, resp);

    }

}