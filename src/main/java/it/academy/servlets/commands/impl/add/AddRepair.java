package it.academy.servlets.commands.impl.add;

import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowRepair;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_REPAIR;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_REPAIR_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long lastBrandId = req.getParameter(BRAND_ID) != null ?
                Long.parseLong(req.getParameter(BRAND_ID)) : DEFAULT_ID;
        log.info(REPAIR_FORM_LAST_BRAND_ID, lastBrandId);

        long selectedBrandId = req.getParameter(SELECTED_BRAND_ID) != null ?
                Long.parseLong(req.getParameter(SELECTED_BRAND_ID)) : DEFAULT_ID;
        log.info(REPAIR_FORM_CURRENT_BRAND_ID, selectedBrandId);

        CreateRepairDTO forCreate = Extractor.extract(req, new CreateRepairDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        if (selectedBrandId == lastBrandId) {
            repairService.addRepair(forCreate);
            return new ShowRepairTable().execute(req, resp);
        }
        return CommandHelper.insertFormData(req,
                REPAIR_TABLE_PAGE_PATH,
                ADD_REPAIR_PAGE_PATH,
                ADD_REPAIR,
                SHOW_REPAIR_TABLE);

    }
}
