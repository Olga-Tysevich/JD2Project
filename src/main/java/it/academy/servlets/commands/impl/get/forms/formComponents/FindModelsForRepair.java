package it.academy.servlets.commands.impl.get.forms.formComponents;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class FindModelsForRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        RepairDTO repair = Extractor.extractObject(req, new RepairDTO());
        String formPagePath = Extractor.extractString(req, PAGE, null);
        long brandId = repair.getBrandId();
        log.info(OBJECT_EXTRACTED_PATTERN, repair);

        RepairFormDTO repairForm = repairService.getRepairForm(brandId);
        repairForm.setRepairDTO(repair);

        req.setAttribute(REPAIR_FORM, repairForm);

        return formPagePath;
    }
}
