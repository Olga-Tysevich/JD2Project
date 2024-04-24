package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.model.ModelsNotFound;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class ShowNewSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);

        try {
            SparePartForChangeDTO model = sparePartService.getSparePartForm();
            req.setAttribute(SPARE_PART, model);
            return CommandHelper.insertFormData(req,
                    SPARE_PART_TABLE_PAGE_PATH,
                    SPARE_PART_PAGE_PATH,
                    ADD_SPARE_PART,
                    SHOW_SPARE_PART_TABLE);
        } catch (IllegalArgumentException | ObjectAlreadyExist | ModelsNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        }
    }

}