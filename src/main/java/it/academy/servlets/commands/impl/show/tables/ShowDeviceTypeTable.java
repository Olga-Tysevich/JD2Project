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
import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.OBJECT_NAME;
import static it.academy.utils.constants.JSPConstant.COMPONENT_FILTERS_PAGE_PATH;

public class ShowDeviceTypeTable implements ActionCommand {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandHelper.checkRole(req);

        Map<String, String> userInput = Extractor.extractParameterMap(req, List.of(OBJECT_NAME));
        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(COMPONENT_FILTERS_PAGE_PATH);
        TablePage<DeviceTypeDTO> brands = deviceTypeService.findForPage(reqData.getPageNumber(),userInput);
        CommandHelper.insertTableData(req, reqData, brands);
        return Extractor.extractMainPagePath(req);
    }

}
