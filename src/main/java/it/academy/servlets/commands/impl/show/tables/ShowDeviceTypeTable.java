package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowDeviceTypeTable extends ShowTable {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);

        TableReq dataForPage = Extractor.extractDataForTable(req);
        ListForPage<DeviceTypeDTO> deviceTypes = deviceTypeService.findDeviceTypes(
                dataForPage.getPageNumber(),
                dataForPage.getFilter(),
                dataForPage.getInput());
        setTableData(req, dataForPage, deviceTypes);
        log.info(CURRENT_TABLE, deviceTypes);
        return ADMIN_MAIN_PAGE_PATH;
    }

}
