package it.academy.servlets.commands.impl.forms;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long deviceTypeId = Long.parseLong(req.getParameter(DEVICE_TYPE_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        DeviceTypeDTO deviceType = deviceTypeService.findDeviceType(deviceTypeId);

        req.setAttribute(DEVICE_TYPE, deviceType);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return DEVICE_TYPE_PAGE_PATH;
    }

}
