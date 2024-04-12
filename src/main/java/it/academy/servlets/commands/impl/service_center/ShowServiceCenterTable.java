package it.academy.servlets.commands.impl.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.ServiceCenterExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowServiceCenterTable implements ActionCommand {
    private Extractor<ServiceCenterDTO> extractor = new ServiceCenterExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.insertAttributes(req);
        req.setAttribute(COMMAND, SHOW_SERVICE_CENTER_TABLE);

        return MAIN_PAGE_PATH;
    }
}