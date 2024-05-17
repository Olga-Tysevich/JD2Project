package it.academy.servlets.commands.impl.show.forms;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.UPDATE_DEVICE_TYPE_PAGE_PATH;

public class ShowUpdateDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long deviceTypeId = Extractor.extractLongVal(req, OBJECT_ID, null);
        DeviceTypeDTO deviceType = deviceTypeService.find(deviceTypeId);
        req.setAttribute(DEVICE_TYPE, deviceType);
        return new ShowForm(UPDATE_DEVICE_TYPE_PAGE_PATH).execute(req, resp);
    }

}
