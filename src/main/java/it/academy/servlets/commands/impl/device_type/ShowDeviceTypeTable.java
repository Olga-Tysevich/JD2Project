package it.academy.servlets.commands.impl.device_type;

import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.TableExtractor;
import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.factory.CommandEnum.SHOW_DEVICE_TYPE_TABLE;
import static it.academy.utils.Constants.*;

public class ShowDeviceTypeTable implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return TableExtractor.extract(req,
                    (a, b, f, c) -> deviceTypeService.findDeviceTypes(a, b, f, c),
                    (a, i) -> deviceTypeService.findDeviceTypes(a, i),
                    SHOW_DEVICE_TYPE_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}
