package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.req.TableReq;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_MODEL_TABLE;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_SERVICE_CENTER_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowModelTable implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ListForPage<ModelDTO> models;
        TableReq pageData = Extractor.extract(req, new TableReq());
        log.info(OBJECT_EXTRACTED_PATTERN, pageData);
        boolean findByFilters = pageData.getFilter() != null && pageData.getInput() != null;

        if (findByFilters) {
            models = modelService.findModels(
                    pageData.getPageNumber(),
                    pageData.getFilter(),
                    pageData.getInput());
        } else {
            models = modelService.findModels(pageData.getPageNumber());
        }

        models.setPage(MODEL_TABLE_PAGE_PATH);
        models.setCommand(SHOW_MODEL_TABLE.name());
        log.info(CURRENT_TABLE, models);
        req.setAttribute(LIST_FOR_PAGE, models);
        return MAIN_PAGE_PATH;
    }
}