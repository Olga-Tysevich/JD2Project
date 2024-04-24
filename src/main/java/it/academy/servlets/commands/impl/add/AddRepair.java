package it.academy.servlets.commands.impl.add;

import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowRepair;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTable;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long lastBrandId = req.getParameter(BRAND_ID) != null ?
                Long.parseLong(req.getParameter(BRAND_ID)) : DEFAULT_ID;
        log.info(REPAIR_FORM_LAST_BRAND_ID, lastBrandId);

        long selectedBrandId = req.getParameter(SELECTED_BRAND_ID) != null ?
                Long.parseLong(req.getParameter(SELECTED_BRAND_ID)) : DEFAULT_ID;
        log.info(REPAIR_FORM_CURRENT_BRAND_ID, selectedBrandId);

        CreateRepairDTO forCreate = Extractor.extract(req, new CreateRepairDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            if (selectedBrandId == lastBrandId) {
                repairService.addRepair(forCreate);
                return new ShowRepairTable().execute(req);
            }
            return new ShowRepair().execute(req);
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }

    }
}
