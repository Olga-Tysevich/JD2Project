package it.academy.servlets.commands.impl.tables;

import it.academy.dto.device.ModelDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.ModelExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowModelTable implements ActionCommand {
    private Extractor<ModelDTO> extractor = new ModelExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.insertAttributes(req);
        req.setAttribute(SHOW_COMMAND, SHOW_MODEL_TABLE);

        return MAIN_PAGE_PATH;
    }
}