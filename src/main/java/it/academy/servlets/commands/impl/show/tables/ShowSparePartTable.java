package it.academy.servlets.commands.impl.show.tables;

import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.TableExtractor;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_TABLE;
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

