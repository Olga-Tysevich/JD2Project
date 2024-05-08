package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage2;
import it.academy.dto.TablePageReq;
import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowModelTable extends ShowTable {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage2<ModelDTO> models;
        switch (filter) {
            case StringUtils.EMPTY:
                models = modelService.findForPage(reqData.getPageNumber());
                break;
            case OBJECT_NAME:
                models = modelService.findByName(reqData.getPageNumber(), reqData.getInput());
                break;
            default:
                models = modelService.findByComponentName(reqData.getPageNumber(), reqData.getFilter(), reqData.getInput());
        }

        CommandHelper.insertTableData(req, reqData, models);
        log.info(CURRENT_TABLE, models);
        return Extractor.extractMainPagePath(req);
    }

}