package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.*;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.DEVICE_TYPE_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.DEVICE_TYPE_TABLE_PAGE_PATH;

public class ShowDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long deviceTypeId = Long.parseLong(req.getParameter(OBJECT_ID));
        DeviceTypeDTO deviceType = deviceTypeService.findDeviceType(deviceTypeId);

        req.setAttribute(DEVICE_TYPE, deviceType);

        return CommandHelper.insertFormData(req,
                DEVICE_TYPE_TABLE_PAGE_PATH,
                DEVICE_TYPE_PAGE_PATH,
                CHANGE_DEVICE_TYPE,
                SHOW_DEVICE_TYPE_TABLE);
    }

}
