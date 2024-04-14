package it.academy.servlets.commands.impl.device_type;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.services.DeviceTypeService;
import it.academy.services.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class ChangeDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> deviceTypeService.updateDeviceType((DeviceTypeDTO) a)),
                    (id) -> deviceTypeService.findDeviceType((Long) id),
                    DeviceTypeDTO.class,
                    DEVICE_TYPE,
                    DEVICE_TYPE_PAGE_PATH,
                    () -> new ShowDeviceTypeTable().execute(req));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }

}
