package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowUpdateSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long sparePartId = Extractor.extractLongVal(req, OBJECT_ID, null);
        SparePartDTO sparePartDTO = sparePartService.find(sparePartId);
        req.setAttribute(SPARE_PART, sparePartDTO);
        return new ShowSparePartForm(UPDATE_SPARE_PART_PAGE_PATH).execute(req, resp);
    }

}
