package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairTypeExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ChangeRepairType implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();
    private Extractor extractor = new RepairTypeExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        RepairTypeDTO repairTypeDTO = (RepairTypeDTO) extractor.getParameter(REPAIR_TYPE);
        adminService.updateRepairType(repairTypeDTO);

        return MAIN_PAGE_PATH;
    }
}
