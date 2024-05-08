package it.academy.servlets.commands.impl.update;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;

public class UpdateDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        DeviceTypeDTO forUpdate = Extractor.extractObject(req, new DeviceTypeDTO());

        try {
            deviceTypeService.update(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(DEVICE_TYPE, forUpdate);
            req.setAttribute(FORM_PAGE, UPDATE_DEVICE_TYPE_PAGE_PATH);
        }
        return Extractor.extractLastPage(req);

    }

}
