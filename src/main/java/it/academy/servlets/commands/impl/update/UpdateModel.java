package it.academy.servlets.commands.impl.update;

import it.academy.dto.device.ModelDTO;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.forms.ShowModelForm;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.UPDATE_MODEL_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_UPDATE_PATTERN;

@Slf4j
public class UpdateModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        ModelDTO forUpdate = Extractor.extractObject(req, new ModelDTO());
        req.setAttribute(MODEL, forUpdate);
        log.info(OBJECT_FOR_UPDATE_PATTERN, forUpdate);

        try {
         modelService.update(forUpdate);
        } catch (ValidationException | BrandsNotFound | DeviceTypesNotFound | ObjectAlreadyExist e) {
            log.error(e.getMessage());
            req.setAttribute(ERROR, e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ShowModelForm(UPDATE_MODEL_PAGE_PATH).execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }

}