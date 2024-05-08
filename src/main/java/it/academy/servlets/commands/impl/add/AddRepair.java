package it.academy.servlets.commands.impl.add;

import it.academy.dto.repair.CreateRepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CreateRepairDTO forCreate = Extractor.extractObject(req, new CreateRepairDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);
        repairService.addRepair(forCreate);

        return Extractor.extractLastPage(req);
    }
}
