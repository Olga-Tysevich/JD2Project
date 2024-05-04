package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage2;
import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
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
public class GetSpareParts implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage2<SparePartDTO> spareParts = null;
        switch (filter) {
            case StringUtils.EMPTY:
                spareParts = sparePartService.findForPage(reqData.getPageNumber());
                break;
            case MODELS:
                spareParts = sparePartService.findForPageByModelName(reqData.getPageNumber(), reqData.getInput());
                break;
            case OBJECT_NAME:
                spareParts = sparePartService.findForPageByName(reqData.getPageNumber(), reqData.getInput());
        }

        CommandHelper.insertTableData(req, reqData, spareParts);
        log.info(CURRENT_TABLE, spareParts);
        return Extractor.extractMainPagePath(req);

    }
}

