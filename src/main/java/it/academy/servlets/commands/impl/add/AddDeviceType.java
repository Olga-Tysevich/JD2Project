package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.services.DeviceTypeService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.DeviceTypeExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private Extractor<DeviceTypeDTO> extractor = new DeviceTypeExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        DeviceTypeDTO deviceType  = extractor.getResult();
        deviceType.setIsActive(true);
        deviceTypeService.addDeviceType(deviceType);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}
