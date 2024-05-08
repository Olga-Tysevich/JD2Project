package it.academy.servlets.commands.impl.show.forms.formComponents;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class FindModelsForRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        RoleEnum role = (RoleEnum) req.getSession().getAttribute(ROLE);
        String formPage = RoleEnum.ADMIN.equals(role) ? ADMIN_REPAIR_PAGE_PATH : ADD_REPAIR_PAGE_PATH;
        RepairDTO repair = Extractor.extractObject(req, new RepairDTO());
        long brandId = repair.getBrandId();
        RepairFormDTO repairForm = repairService.getRepairForm(brandId);
        repairForm.setRepairDTO(repair);
        req.setAttribute(REPAIR_FORM, repairForm);
        req.setAttribute(FORM_PAGE, formPage);
        return Extractor.extractLastPage(req);
    }
}
