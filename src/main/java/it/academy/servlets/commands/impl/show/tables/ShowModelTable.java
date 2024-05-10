package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.MODEL_FILTERS_PAGE_PATH;

public class ShowModelTable implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        TablePageReq reqData = Extractor.extractDataForTable(req, MODEL_FILTERS_PAGE_PATH);
        TablePage<ModelDTO> models = modelService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, models);
        return Extractor.extractMainPagePath(req);
    }

}