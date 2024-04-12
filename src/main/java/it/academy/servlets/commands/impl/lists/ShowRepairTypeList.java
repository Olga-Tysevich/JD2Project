package it.academy.servlets.commands.impl.lists;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowRepairTypeList implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long repairId = Long.parseLong(req.getParameter(REPAIR_ID));
        List<RepairTypeDTO> repairTypes = repairService.findRepairTypes();

        req.setAttribute(REPAIR_ID, repairId);
        req.setAttribute(REPAIR_TYPES, repairTypes);

        return REPAIR_TYPE_LIST_PAGE_PATH;
    }

}
