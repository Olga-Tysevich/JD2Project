package it.academy.servlets.commands.impl.service_center;

import it.academy.services.service_center.ServiceCenterService;
import it.academy.services.service_center.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.factory.CommandEnum.SHOW_SERVICE_CENTER_TABLE;

public class ShowServiceCenterTable implements ActionCommand {
   private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        return TableExtractor.extract(req,
                (b, f, c) -> serviceCenterService.findServiceCenters(b, f, c),
                (i) -> serviceCenterService.findServiceCenters(i),
                SHOW_SERVICE_CENTER_TABLE);

    }

}