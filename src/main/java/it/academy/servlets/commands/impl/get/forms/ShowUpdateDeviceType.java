package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.UPDATE_DEVICE_TYPE_PAGE_PATH;

public class ShowUpdateDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long deviceTypeId = Long.parseLong(req.getParameter(OBJECT_ID));
        DeviceTypeDTO deviceType = deviceTypeService.find(deviceTypeId);
        req.setAttribute(DEVICE_TYPE, deviceType);
        return new ShowForm(UPDATE_DEVICE_TYPE_PAGE_PATH).execute(req,resp);
    }

}
