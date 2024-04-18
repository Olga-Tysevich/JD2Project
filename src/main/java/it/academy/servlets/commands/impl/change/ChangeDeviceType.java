package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.services.DeviceTypeService;
import it.academy.services.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowDeviceTypeTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.ERROR_PAGE_PATH;

@Slf4j
public class ChangeDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            String result =  FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> deviceTypeService.updateDeviceType((DeviceTypeDTO) a)),
                    (id) -> deviceTypeService.findDeviceType((Long) id),
                    DeviceTypeDTO.class,
                    DEVICE_TYPE,
                    DEVICE_TYPE_PAGE_PATH,
                    () -> new ShowDeviceTypeTable().execute(req));
            return result;
        } catch (Exception e) {
            return ERROR_PAGE_PATH;
        }

    }

}
