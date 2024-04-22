package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.repair.RepairFormDTO;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_REPAIR;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE;
import static it.academy.utils.constants.Constants.*;

@Slf4j
public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
//
//        try {
//            long brandId = Long.parseLong(req.getParameter(SELECTED_BRAND_ID));
//            String tablePage = req.getParameter(PAGE);
//            int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
//            RepairFormDTO repairForm = repairService.getRepairFormData(brandId);
//            req.setAttribute(BRAND_ID, brandId);
//            req.setAttribute(SELECTED_BRAND_ID, brandId);
//            req.setAttribute(REPAIR_FORM, repairForm);
//            req.setAttribute(PAGE_NUMBER, pageNumber);
//            req.setAttribute(PAGE, tablePage);
//            return ADD_REPAIR_PAGE_PATH;
//
//        } catch (BrandsNotFound | ModelNotFound e) {
//            req.setAttribute(ERROR, e.getMessage());
//            return MAIN_PAGE_PATH;
//        } catch (Exception e) {
//            req.setAttribute(ERROR, ERROR_MESSAGE);
//            return ERROR_PAGE_PATH;
//        }


        try {
            long brandId = Long.parseLong(req.getParameter(SELECTED_BRAND_ID));
            String tablePage = req.getParameter(PAGE);
            int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
            RepairFormDTO repairForm = repairService.getRepairFormData(brandId);
            req.setAttribute(BRAND_ID, brandId);
            req.setAttribute(SELECTED_BRAND_ID, brandId);
            req.setAttribute(REPAIR_FORM, repairForm);
            req.setAttribute(PAGE_NUMBER, pageNumber);

            req.setAttribute(PAGE, REPAIR_TABLE_PAGE_PATH);
            req.setAttribute(FORM_PAGE, "/pages/repair/addRepairPageTest.jsp");
            req.setAttribute(COMMAND, ADD_REPAIR);
            req.setAttribute(DISPLAY_TABLE_COMMAND, SHOW_REPAIR_TABLE);
            return "/pages/forms/changeForm.jsp";

        } catch (BrandsNotFound | ModelNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }

}
