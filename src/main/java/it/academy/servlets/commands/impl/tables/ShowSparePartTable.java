package it.academy.servlets.commands.impl.tables;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.services.AdminService;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.TableManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class ShowSparePartTable implements ActionCommand {
    private SparePartOrderService service = new SparePartOrderServiceImpl();
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<SparePartDTO> orders;
        List<DeviceTypeDTO> deviceTypes = adminService.findDeviceTypes();

        if (input != null && !input.isBlank()) {
            orders = service.findSpareParts(pageNumber, filter, input);
        } else {
            orders = service.findSpareParts(pageNumber);
        }

        TableManager.insertAttributesForTable(req, orders, SPARE_PART_TABLE_TYPE_PAGE_PATH);
        req.setAttribute(SHOW_COMMAND, SHOW_SPARE_PART_TABLE);
        req.setAttribute(DEVICE_TYPES, deviceTypes);

        return MAIN_PAGE_PATH;
    }

}
