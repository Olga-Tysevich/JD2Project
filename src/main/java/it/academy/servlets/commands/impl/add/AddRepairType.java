package it.academy.servlets.commands.impl.add;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddRepairType implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        RepairTypeDTO forCreate = Extractor.extractObject(req, new RepairTypeDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);
        try {
            repairTypeService.create(forCreate);
        } catch (ValidationException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(REPAIR_TYPE, forCreate.getName());
            req.setAttribute(FORM_PAGE, ADD_REPAIR_TYPE_PAGE_PATH);
        }
        return Extractor.extractLastPage(req);
    }

}
