package it.academy.servlets.commands.impl.show.tables;

import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_SERVICE_CENTER_TABLE;
import static it.academy.utils.constants.Constants.ERROR_PAGE_PATH;

public class ShowServiceCenterTable implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
//            return TableExtractor.extract(req,
//                    (a, p, f, c) -> serviceCenterService.findServiceCenters(a, p, f, c),
//                    (a, p) -> serviceCenterService.findServiceCenters(a, p),
//                    SHOW_SERVICE_CENTER_TABLE);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }

}