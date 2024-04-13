package it.academy.servlets.commands.impl.service_center;

import it.academy.services.service_center.ServiceCenterService;
import it.academy.services.service_center.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_SERVICE_CENTER_TABLE;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ShowServiceCenterTable implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, p, f, c) -> serviceCenterService.findServiceCenters(a, p, f, c),
                    (a, p) -> serviceCenterService.findServiceCenters(a, p),
                    SHOW_SERVICE_CENTER_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }

}