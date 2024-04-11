package it.academy.servlets.commands.impl.delete;

import it.academy.services.spare_part.SparePartService;
import it.academy.services.spare_part.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.SparePartExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.CURRENT_SPARE_PART_ID;
import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class DeleteSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();
    private SparePartExtractor extractor = new SparePartExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long id = Long.parseLong(req.getParameter(CURRENT_SPARE_PART_ID));
        sparePartService.deleteSparePart(id);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
