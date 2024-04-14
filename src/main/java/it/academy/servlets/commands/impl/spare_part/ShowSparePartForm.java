//package it.academy.servlets.commands.impl.forms;
//
//import it.academy.dto.req.DeviceTypeDTO;
//import it.academy.dto.req.SparePartDTO;
//import it.academy.services.spare_part.SparePartOrderService;
//import it.academy.services.DeviceTypeService;
//import it.academy.services.impl.DeviceTypeServiceImpl;
//import it.academy.services.spare_part.SparePartService;
//import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
//import it.academy.services.spare_part.impl.SparePartServiceImpl;
//import it.academy.servlets.commands.ActionCommand;
//import javax.servlet.http.HttpServletRequest;
//
//import java.util.List;
//
//import static it.academy.utils.Constants.*;
//
//public class ShowSparePartForm implements ActionCommand {
//    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
//    private SparePartService sparePartService = new SparePartServiceImpl();
//    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//        long sparePartId = Long.parseLong(req.getParameter(CURRENT_SPARE_PART_ID));
//        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
//        SparePartDTO sparePart = sparePartService.findSparePart(sparePartId);
//        List<DeviceTypeDTO> deviceTypes = deviceTypeService.findDeviceTypes();
//
//        req.setAttribute(CURRENT_SPARE_PART, sparePart);
//        req.setAttribute(PAGE_NUMBER, pageNumber);
//        req.setAttribute(DEVICE_TYPES, deviceTypes);
//
//        return SPARE_PART_PAGE_PATH;
//    }
//
//}