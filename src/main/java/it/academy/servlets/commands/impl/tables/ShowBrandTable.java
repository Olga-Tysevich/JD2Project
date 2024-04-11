package it.academy.servlets.commands.impl.tables;

import it.academy.dto.device.BrandDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.BrandExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowBrandTable implements ActionCommand {
    private Extractor<BrandDTO> extractor = new BrandExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.insertAttributes(req);
        req.setAttribute(COMMAND, SHOW_BRAND_TABLE);

        return MAIN_PAGE_PATH;
    }

}