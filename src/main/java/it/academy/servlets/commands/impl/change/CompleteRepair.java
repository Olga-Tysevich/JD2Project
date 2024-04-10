package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class CompleteRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        long repairTypeId = Long.parseLong(req.getParameter(REPAIR_TYPE_ID));
        RepairDTO repair = repairService.findRepair(repairId);

        RepairTypeDTO repairTypeDTO = RepairTypeDTO.builder()
                .id(repairTypeId)
                .code(req.getParameter(REPAIR_TYPE_CODE))
                .level(req.getParameter(REPAIR_TYPE_LEVEL))
                .name(REPAIR_TYPE_NAME)
                .build();
        repair.setRepairType(repairTypeDTO);
        repair.setStatus(RepairStatus.COMPLETED);

        repairService.updateRepair(repair);

        return String.format(OPEN_REPAIR_PAGE, repairId);
    }

}
