package it.academy.servlets.commands.impl.update;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.tables.ShowRepairTypeTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_REPAIR_TYPE;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TYPE_TABLE;
import static it.academy.utils.constants.Constants.ERROR;
import static it.academy.utils.constants.JSPConstant.REPAIR_TYPE_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.REPAIR_TYPE_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("in change repairType");
        CommandHelper.checkRole(req);
        RepairTypeDTO forUpdate = Extractor.extractObject(req, new RepairTypeDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            repairTypeService.update(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    REPAIR_TYPE_TABLE_PAGE_PATH,
                    REPAIR_TYPE_PAGE_PATH,
                    CHANGE_REPAIR_TYPE,
                    SHOW_REPAIR_TYPE_TABLE);
        }
        return new ShowRepairTypeTable().execute(req, resp);

    }

}