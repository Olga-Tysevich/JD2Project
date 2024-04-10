package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairTypeExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ChangeRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();
    private Extractor<RepairTypeDTO> extractor = new RepairTypeExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        RepairTypeDTO repairTypeDTO = extractor.getResult();
        repairTypeService.updateRepairType(repairTypeDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }
}
