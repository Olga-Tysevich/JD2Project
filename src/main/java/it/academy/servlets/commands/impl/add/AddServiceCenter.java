package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowServiceCenterTable;
import it.academy.servlets.extractors.impl.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> serviceCenterService.addServiceCenter((ServiceCenterDTO) a)),
                    (id) -> serviceCenterService.findServiceCenter((Long) id),
                    ServiceCenterDTO.class,
                    SERVICE_CENTER,
                    SERVICE_CENTER_PAGE_PATH,
                    () -> new ShowServiceCenterTable().execute(req));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }

}