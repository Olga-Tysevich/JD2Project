package it.academy.servlets.commands.impl.change;

import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairTypeDTO;
import it.academy.utils.enums.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class CompleteRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        long repairTypeId = Long.parseLong(req.getParameter(REPAIR_TYPE_ID));
//        RepairDTO repair = repairService.findRepair(repairId);

        RepairTypeDTO repairTypeDTO = RepairTypeDTO.builder()
                .id(repairTypeId)
                .code(req.getParameter(REPAIR_TYPE_CODE))
                .level(req.getParameter(REPAIR_TYPE_LEVEL))
                .name(REPAIR_TYPE_NAME)
                .build();
//        repair.setRepairType(repairTypeDTO);
//        repair.setStatus(RepairStatus.COMPLETED);
//
//        repairService.updateRepair(repair);

        return String.format(OPEN_REPAIR_PAGE, repairId);
    }

}
