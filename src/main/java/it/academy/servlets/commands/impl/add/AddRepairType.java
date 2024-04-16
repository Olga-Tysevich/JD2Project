package it.academy.servlets.commands.impl.add;

import it.academy.dto.resp.RepairTypeDTO;
import it.academy.services.RepairTypeService;
import it.academy.services.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.RepairTypeExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();
    private RepairTypeExtractor extractor = new RepairTypeExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.extractValues(req);

//        RepairTypeDTO repairTypeDTO = extractor.getResult();
//        repairTypeService.addRepairType(repairTypeDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
