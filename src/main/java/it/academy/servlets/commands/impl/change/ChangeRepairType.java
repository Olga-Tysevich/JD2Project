package it.academy.servlets.commands.impl.change;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowRepairType;
import it.academy.servlets.commands.impl.show.tables.ShowRepairTypeTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.ERROR;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);
        RepairTypeDTO forUpdate = Extractor.extract(req, new RepairTypeDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            repairTypeService.updateRepairType(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return new ShowRepairType().execute(req);
        }
        return new ShowRepairTypeTable().execute(req);

    }

}