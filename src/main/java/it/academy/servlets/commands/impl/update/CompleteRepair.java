package it.academy.servlets.commands.impl.update;

import it.academy.dto.repair.CompleteRepairDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.GetRepair;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.OBJECT_ID;
import static it.academy.utils.constants.JSPConstant.REPAIR_TYPE_ID;

public class CompleteRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Long repairId = Extractor.extractLongVal(req, OBJECT_ID, null);
        Long repairTypeId = Extractor.extractLongVal(req, REPAIR_TYPE_ID, null);
        CompleteRepairDTO completeRepairDTO = CompleteRepairDTO.builder()
                .repairId(repairId)
                .repairTypeId(repairTypeId)
                .build();

        repairService.completeRepair(completeRepairDTO);

        return new GetRepair().execute(req, resp);
    }

}
