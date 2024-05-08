package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import static it.academy.utils.constants.Constants.*;

public class ShowSparePartTable implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage<SparePartDTO> spareParts = null;
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
        return Extractor.extractMainPagePath(req);

    }
}

