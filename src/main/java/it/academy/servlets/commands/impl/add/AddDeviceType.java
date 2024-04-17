package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.DeviceTypeService;
import it.academy.services.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowDeviceTypeTable;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class AddDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        DeviceTypeDTO deviceTypeDTO = Extractor.extract(req, new DeviceTypeDTO());

        try {
            deviceTypeService.createDeviceType(deviceTypeDTO);
        } catch (IllegalArgumentException | AccessDenied e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), deviceTypeDTO));
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowDeviceTypeTable().execute(req);
    }

}
