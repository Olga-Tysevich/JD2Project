package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.ChangeRepairFormDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_REPAIR;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.REPAIR_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class ChangeRepair extends AddRepair {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long lastBrandId = Long.parseLong(req.getParameter(BRAND_ID));
        log.info(REPAIR_FORM_LAST_BRAND_ID, lastBrandId);

        long selectedBrandId = Long.parseLong(req.getParameter(SELECTED_BRAND_ID));
        log.info(REPAIR_FORM_CURRENT_BRAND_ID, selectedBrandId);

        RepairDTO forUpdate = Extractor.extract(req, new RepairDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        if (selectedBrandId == lastBrandId) {
            repairService.updateRepair(forUpdate);
            return new ShowRepairTable().execute(req, resp);
        }
        RepairFormDTO repairForm = repairService.getRepairFormData(selectedBrandId);
        ChangeRepairFormDTO changeRepairFormDTO = new ChangeRepairFormDTO(forUpdate, repairForm);

        req.setAttribute(BRAND_ID, selectedBrandId);
        req.setAttribute(SELECTED_BRAND_ID, selectedBrandId);
        req.setAttribute(CHANGE_REPAIR_FORM, changeRepairFormDTO);

        return CommandHelper.insertFormData(req,
                REPAIR_TABLE_PAGE_PATH,
                REPAIR_PAGE_PATH,
                CHANGE_REPAIR,
                SHOW_REPAIR_TABLE);
    }

}
