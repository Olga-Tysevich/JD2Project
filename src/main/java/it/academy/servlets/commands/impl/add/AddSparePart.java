package it.academy.servlets.commands.impl.add;

import it.academy.dto.spare_part.SparePartDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowSparePartForm;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_SPARE_PART_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SPARE_PART;

@Slf4j
public class AddSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        SparePartDTO forCreate = Extractor.extractSparePart(req);

        try {
            sparePartService.create(forCreate);
        } catch (IllegalArgumentException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(SPARE_PART, forCreate);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ShowSparePartForm(ADD_SPARE_PART_PAGE_PATH).execute(req, resp);
        }

        return Extractor.extractLastPage(req);
    }

}
