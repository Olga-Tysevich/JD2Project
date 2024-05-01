package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.tables.ShowDeviceTypeTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_DEVICE_TYPE;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_DEVICE_TYPE_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.DEVICE_TYPE_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.DEVICE_TYPE_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        DeviceTypeDTO forUpdate = Extractor.extract(req, new DeviceTypeDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            deviceTypeService.updateDeviceType(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    DEVICE_TYPE_TABLE_PAGE_PATH,
                    DEVICE_TYPE_PAGE_PATH,
                    CHANGE_DEVICE_TYPE,
                    SHOW_DEVICE_TYPE_TABLE);
        }
        return new ShowDeviceTypeTable().execute(req, resp);

    }

}
