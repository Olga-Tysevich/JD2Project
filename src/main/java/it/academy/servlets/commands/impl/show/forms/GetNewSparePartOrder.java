package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_SPARE_PART_ORDER_PAGE_PATH;

public class GetNewSparePartOrder implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairId = Extractor.extractLongVal(req, OBJECT_ID, DEFAULT_ID);
        long modelId = Extractor.extractLongVal(req, MODEL_ID, DEFAULT_ID);
        String repairNumber = Extractor.extractString(req, REPAIR_NUMBER, StringUtils.EMPTY);
        List<SparePartDTO> dtoList = sparePartService.findSparePartsByModelId(modelId);

        req.setAttribute(SPARE_PARTS, dtoList);
        req.setAttribute(OBJECT_ID, repairId);
        req.setAttribute(MODEL_ID, modelId);
        req.setAttribute(REPAIR_NUMBER, repairNumber);

        return ADD_SPARE_PART_ORDER_PAGE_PATH;
    }

}
