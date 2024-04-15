package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.services.DeviceTypeService;
import it.academy.services.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ShowDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        long deviceTypeId = Long.parseLong(req.getParameter(OBJECT_ID));
        String page = req.getParameter(PAGE);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        DeviceTypeDTO deviceType = deviceTypeService.findDeviceType(deviceTypeId);

        req.setAttribute(DEVICE_TYPE, deviceType);
        req.setAttribute(PAGE, page);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return DEVICE_TYPE_PAGE_PATH;
    }

}