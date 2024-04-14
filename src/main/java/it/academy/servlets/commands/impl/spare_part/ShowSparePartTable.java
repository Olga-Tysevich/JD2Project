package it.academy.servlets.commands.impl.spare_part;

import it.academy.services.spare_part.SparePartService;
import it.academy.services.spare_part.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_SPARE_PART_TABLE;
import static it.academy.utils.Constants.*;

public class ShowSparePartTable implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, b, f, c) -> sparePartService.findSpareParts(a, b, f, c),
                    (a, i) -> sparePartService.findSpareParts(a, i),
                    SHOW_SPARE_PART_TABLE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }
}

