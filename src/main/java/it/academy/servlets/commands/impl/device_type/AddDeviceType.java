package it.academy.servlets.commands.impl.device_type;

import it.academy.dto.device.req.DeviceTypeDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        DeviceTypeDTO deviceTypeDTO = ExtractorImpl.extract(req, new DeviceTypeDTO());
        try {
            deviceTypeService.createDeviceType(deviceTypeDTO);
        } catch (IllegalArgumentException | AccessDenied e) {
            System.out.println("error " + e.getMessage());
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowDeviceTypeTable().execute(req);
    }

}
