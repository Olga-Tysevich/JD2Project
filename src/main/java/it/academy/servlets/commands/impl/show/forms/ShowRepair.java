package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.ChangeRepairFormDTO;
import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.Builder;
import it.academy.utils.CommandHelper;
import it.academy.utils.enums.RoleEnum;
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
            case SHOW_REPAIR:
                return showNewRepair(req);
            case SHOW_CONFIRMED_REPAIR:
                return showRepair(req);
            default:
                return ERROR_PAGE_PATH;
        }

    }

    private String showNewRepair(HttpServletRequest req) {
        try {
            AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

            long brandId = Extractor.extractLongVal(req, SELECTED_BRAND_ID, DEFAULT_ID);
            long serviceCenterId = accountDTO.getServiceCenterId();

            RepairFormDTO repairForm = repairService.getRepairFormData(brandId);
            CreateRepairDTO createRepairDTO = Builder.buildEmptyNewRepair(serviceCenterId);
            req.setAttribute(CREATE_REPAIR_FORM, createRepairDTO);
            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(REPAIR_FORM, repairForm);

            return CommandHelper.insertFormData(req,
                    REPAIR_TABLE_PAGE_PATH,
                    ADD_REPAIR_PAGE_PATH,
                    ADD_REPAIR,
                    SHOW_REPAIR_TABLE);

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return USER_MAIN_PAGE_PATH;
        }
    }

    private String showRepair(HttpServletRequest req) {
        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        RoleEnum role = accountDTO.getRole();
        try {
            long repairId = Extractor.extractLongVal(req, OBJECT_ID, DEFAULT_ID);
            ChangeRepairFormDTO changeRepairForm = repairService.findRepair(repairId);
            long brandId = changeRepairForm.getRepairDTO().getBrandId();

            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(CHANGE_REPAIR_FORM, changeRepairForm);

            CommandHelper.insertFormData(req,
                    REPAIR_TABLE_PAGE_PATH,
                    REPAIR_PAGE_PATH,
                    CHANGE_REPAIR,
                    SHOW_REPAIR_TABLE);

            return REPAIR_PAGE_PATH;

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return RoleEnum.ADMIN.equals(role) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
        }
    }

}
