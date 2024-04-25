package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_DEVICE_TYPE_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowDeviceTypeTable implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        ListForPage<DeviceTypeDTO> deviceTypes;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        deviceTypes = deviceTypeService.findDeviceTypes(
                dataFromPage.getPageNumber(),
                dataFromPage.getFilter(),
                dataFromPage.getInput());
        deviceTypes.setPage(dataFromPage.getPage());
        deviceTypes.setCommand(SHOW_DEVICE_TYPE_TABLE.name());
        req.setAttribute(LIST_FOR_PAGE, deviceTypes);
        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
        log.info(CURRENT_TABLE, deviceTypes);
        return ADMIN_MAIN_PAGE_PATH;
    }

}
