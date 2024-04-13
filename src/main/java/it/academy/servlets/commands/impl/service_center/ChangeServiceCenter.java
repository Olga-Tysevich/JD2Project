package it.academy.servlets.commands.impl.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.service_center.ServiceCenterService;
import it.academy.services.service_center.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

import static it.academy.utils.Constants.*;

public class ChangeServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> serviceCenterService.updateServiceCenter((ServiceCenterDTO) a)),
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