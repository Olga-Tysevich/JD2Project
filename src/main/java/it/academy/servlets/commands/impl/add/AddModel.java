package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.ModelDTO;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowModelForm;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_MODEL_PAGE_PATH;

public class AddModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);
        ModelDTO forCreate = Extractor.extractObject(req, new ModelDTO());
        req.setAttribute(MODEL, forCreate);

        try {
            modelService.create(forCreate);
        } catch (ValidationException | BrandsNotFound | DeviceTypesNotFound | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ShowModelForm(ADD_MODEL_PAGE_PATH).execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }

}
