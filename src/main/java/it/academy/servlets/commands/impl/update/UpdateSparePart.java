package it.academy.servlets.commands.impl.update;

import it.academy.dto.spare_part.SparePartDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.get.forms.ShowSparePartForm;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

@Slf4j
public class UpdateSparePart extends AddSparePart {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        SparePartDTO forUpdate = Extractor.extractSparePart(req);
        try {
            sparePartService.update(forUpdate);
        } catch (IllegalArgumentException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SPARE_PART, forUpdate);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ShowSparePartForm(UPDATE_SPARE_PART_PAGE_PATH).execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }
}
