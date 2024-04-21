package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.services.device.SparePartService;
import it.academy.services.device.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowSparePartTable implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ListForPage<SparePartDTO> spareParts;
        TableReq pageData = Extractor.extract(req, new TableReq());
        log.info(OBJECT_EXTRACTED_PATTERN, pageData);
        boolean findByFilters = pageData.getFilter() != null && pageData.getInput() != null;

        if (findByFilters) {
            spareParts = sparePartService.findSpareParts(
                    pageData.getPageNumber(),
                    pageData.getFilter(),
                    pageData.getInput());
        } else {
            spareParts = sparePartService.findSpareParts(pageData.getPageNumber());
        }

        spareParts.setPage(SPARE_PART_TABLE_PAGE_PATH);
        spareParts.setCommand(SHOW_SPARE_PART_TABLE.name());
        log.info(CURRENT_TABLE, spareParts);
        req.setAttribute(LIST_FOR_PAGE, spareParts);
        return MAIN_PAGE_PATH;

    }
}

