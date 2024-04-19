package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.req.TableReq;
import it.academy.dto.resp.ListForPage;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;

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
        deviceTypes.setCommand(dataFromPage.getCommand());
        req.setAttribute(LIST_FOR_PAGE, deviceTypes);
        return MAIN_PAGE_PATH;
    }

}
