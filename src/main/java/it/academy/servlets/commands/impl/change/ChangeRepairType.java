package it.academy.servlets.commands.impl.change;

import it.academy.services.RepairTypeService;
import it.academy.services.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();
//    private Extractor<RepairTypeDTO> extractor = new RepairTypeExtractor();

    @Override
    public String execute(HttpServletRequest req) {

//        extractor.extractValues(req);
//
//        RepairTypeDTO repairTypeDTO = extractor.getResult();
//        repairTypeService.updateRepairType(repairTypeDTO);
//
//        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }
}
