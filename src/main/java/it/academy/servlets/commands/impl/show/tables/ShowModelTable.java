package it.academy.servlets.commands.impl.show.tables;

import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_MODEL_TABLE;
import static it.academy.utils.constants.Constants.*;

public class ShowModelTable implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, b, f, c) -> modelService.findModels(a, b, f, c),
                    (a, i) -> modelService.findModels(a, i),
                    SHOW_MODEL_TABLE);
        } catch (BrandsNotFound | DeviceTypesNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        } catch (Error e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }
}