package it.academy.servlets.commands.impl.update;

import it.academy.dto.repair.RepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.extractors.Extractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRepair extends AddRepair {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        RepairDTO forUpdate = Extractor.extractObject(req, new RepairDTO());
        repairService.updateRepair(forUpdate);
        return Extractor.extractLastPage(req);
    }

}
