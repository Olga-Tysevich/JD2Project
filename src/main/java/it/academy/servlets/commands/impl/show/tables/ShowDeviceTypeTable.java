package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_DEVICE_TYPE_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowDeviceTypeTable implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        ListForPage<DeviceTypeDTO> deviceTypes;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        boolean findByFilters = dataFromPage.getFilter() != null && dataFromPage.getInput() != null;

        if (findByFilters) {
            deviceTypes = deviceTypeService.findDeviceTypes(
                    dataFromPage.getPageNumber(),
                    dataFromPage.getFilter(),
                    dataFromPage.getInput());
        } else {
            deviceTypes = deviceTypeService.findDeviceTypes(dataFromPage.getPageNumber());
        }

        deviceTypes.setPage(dataFromPage.getPage());
        deviceTypes.setCommand(SHOW_DEVICE_TYPE_TABLE.name());
        log.info(CURRENT_TABLE, deviceTypes);
        req.setAttribute(LIST_FOR_PAGE, deviceTypes);
        return MAIN_PAGE_PATH;
    }

}
