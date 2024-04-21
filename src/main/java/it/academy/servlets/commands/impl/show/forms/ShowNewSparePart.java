package it.academy.servlets.commands.impl.show.forms;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.model.ModelsNotFound;
import it.academy.services.device.SparePartService;
import it.academy.services.device.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_SPARE_PART;
import static it.academy.utils.constants.Constants.*;

public class ShowNewSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));

        try {
            SparePartForChangeDTO model = sparePartService.getSparePartForm();
            req.setAttribute(SPARE_PART, model);
            req.setAttribute(PAGE, page);
            req.setAttribute(COMMAND, ADD_SPARE_PART);
            req.setAttribute(PAGE_NUMBER, pageNumber);
            return SPARE_PART_PAGE_PATH;
        } catch (IllegalArgumentException | ObjectAlreadyExist | ModelsNotFound e) {
            req.setAttribute(ERROR, e.getMessage());
            return MAIN_PAGE_PATH;
        }
    }

}