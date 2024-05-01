package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.repair.ChangeRepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.ERROR;
import static it.academy.utils.constants.JSPConstant.*;

public class GetRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            long repairId = Extractor.extractLongVal(req, OBJECT_ID, DEFAULT_ID);
            ChangeRepairFormDTO changeRepairForm = repairService.findRepair(repairId);
            long brandId = changeRepairForm.getRepairDTO().getBrandId();

            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(CHANGE_REPAIR_FORM, changeRepairForm);

            return REPAIR_PAGE_PATH;

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return (String) req.getSession().getAttribute(MAIN_PAGE_PATH);
        }

    }
}