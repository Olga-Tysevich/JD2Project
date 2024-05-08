package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADD_DEVICE_TYPE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        DeviceTypeDTO forCreate = Extractor.extractObject(req, new DeviceTypeDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            deviceTypeService.create(forCreate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            req.setAttribute(OBJECT_NAME, forCreate.getName());
            req.setAttribute(FORM_PAGE, ADD_DEVICE_TYPE_PAGE_PATH);
        }

        return Extractor.extractLastPage(req);
    }

}
