package it.academy.servlets.commands.impl.models;

import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.factory.CommandEnum.SHOW_BRAND_TABLE;
import static it.academy.utils.Constants.*;

public class ShowModelTable implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, b, f, c) -> modelService.findModels(a, b, f, c),
                    (a, i) -> modelService.findModels(a, i),
                    SHOW_BRAND_TABLE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }
}