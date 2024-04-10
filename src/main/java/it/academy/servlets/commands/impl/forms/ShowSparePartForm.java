package it.academy.servlets.commands.impl.forms;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.services.SparePartOrderService;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowSparePartForm implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long sparePartId = Long.parseLong(req.getParameter(CURRENT_SPARE_PART_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        SparePartDTO sparePart = sparePartOrderService.findSparePart(sparePartId);
        List<DeviceTypeDTO> deviceTypes = deviceTypeService.findDeviceTypes();

        req.setAttribute(CURRENT_SPARE_PART, sparePart);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(DEVICE_TYPES, deviceTypes);

        return SPARE_PART_PAGE_PATH;
    }

}
