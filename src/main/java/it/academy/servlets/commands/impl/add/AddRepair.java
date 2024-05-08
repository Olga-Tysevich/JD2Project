package it.academy.servlets.commands.impl.add;

import it.academy.dto.repair.CreateRepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CreateRepairDTO forCreate = Extractor.extractObject(req, new CreateRepairDTO());
        repairService.addRepair(forCreate);
        return Extractor.extractLastPage(req);
    }
}
