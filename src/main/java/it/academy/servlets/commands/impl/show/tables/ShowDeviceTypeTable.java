package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.TablePageReq;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.COMPONENT_FILTERS_PAGE_PATH;

public class ShowDeviceTypeTable implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        TablePageReq reqData = Extractor.extractDataForTable(req, COMPONENT_FILTERS_PAGE_PATH);
        TablePage<DeviceTypeDTO> deviceTypes = deviceTypeService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, deviceTypes);
        return Extractor.extractMainPagePath(req);
    }

}
