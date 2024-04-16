package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowServiceCenterTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

@Slf4j
public class ChangeServiceCenter implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            String result = FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> serviceCenterService.updateServiceCenter((ServiceCenterDTO) a)),
                    (id) -> serviceCenterService.findServiceCenter((Long) id),
                    ServiceCenterDTO.class,
                    SERVICE_CENTER,
                    SERVICE_CENTER_PAGE_PATH,
                    () -> new ShowServiceCenterTable().execute(req));
            log.info(String.format(CURRENT_PAGE, result));
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}