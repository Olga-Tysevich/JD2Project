package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowDeviceTypeTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class AddDeviceType implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
        CommandHelper.checkRole(accountDTO);
        DeviceTypeDTO forCreate = Extractor.extract(req, new DeviceTypeDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            deviceTypeService.createDeviceType(forCreate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
        }
        return new ShowDeviceTypeTable().execute(req);
    }

}
