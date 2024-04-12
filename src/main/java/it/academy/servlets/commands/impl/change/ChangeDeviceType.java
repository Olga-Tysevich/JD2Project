package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.req.DeviceTypeDTO;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.DeviceTypeExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class ChangeDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private Extractor<DeviceTypeDTO> extractor = new DeviceTypeExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.extractValues(req);
        DeviceTypeDTO deviceType = extractor.getResult();
        deviceTypeService.updateDeviceType(deviceType);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
