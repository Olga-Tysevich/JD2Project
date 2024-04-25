package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.repair.ChangeRepairFormDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

@Slf4j
public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandEnum command = CommandEnum.valueOf(req.getParameter(COMMAND));
        switch (command) {
            case SHOW_REPAIR: return showNewRepair(req);
            case SHOW_CONFIRMED_REPAIR: return showRepair(req);
            default: return ADMIN_MAIN_PAGE_PATH;
        }

    }

    private String showNewRepair(HttpServletRequest req) {
        try {
            long brandId = Long.parseLong(req.getParameter(SELECTED_BRAND_ID));
            RepairFormDTO repairForm = repairService.getRepairFormData(brandId);
            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(REPAIR_FORM, repairForm);

            return insertAttributes(req, ADD_REPAIR_PAGE_PATH, ADD_REPAIR);

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return USER_MAIN_PAGE_PATH;
        }
    }

    private String showRepair(HttpServletRequest req) {

        try {
            long repairId = Long.parseLong(req.getParameter(OBJECT_ID));
            ChangeRepairFormDTO changeRepairForm = repairService.findRepair(repairId);
            long brandId = changeRepairForm.getRepairDTO().getDeviceDTO().getId();

            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(CHANGE_REPAIR_FORM, changeRepairForm);

            return insertAttributes(req, REPAIR_PAGE_PATH, CHANGE_REPAIR);

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }

    private String insertAttributes(HttpServletRequest req, String pagePath, CommandEnum command) {

        return CommandHelper.insertFormData(req,
                REPAIR_TABLE_PAGE_PATH,
                pagePath,
                command,
                SHOW_REPAIR_TABLE);
    }

}
