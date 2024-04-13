//package it.academy.servlets.extractors.impl;
//
//import it.academy.dto.table.resp.ListForPage;
//import it.academy.dto.device.req.DeviceTypeDTO;
//import it.academy.dto.spare_parts.SparePartDTO;
//import it.academy.services.spare_part.SparePartOrderService;
//import it.academy.services.device.DeviceTypeService;
//import it.academy.services.device.impl.DeviceTypeServiceImpl;
//import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
//import it.academy.servlets.extractors.Extractor;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//import static it.academy.utils.Constants.*;
//
//public class SparePartExtractor implements Extractor<SparePartDTO> {
//    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
//    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
//    private SparePartDTO sparePart;
//
//
//    @Override
//    public void extractValues(HttpServletRequest req) {
//        Long id = req.getParameter(OBJECT_ID) != null ? Long.parseLong(req.getParameter(OBJECT_ID)) : null;
//        String sparePartName = req.getParameter(OBJECT_NAME);
//
//        this.sparePart = SparePartDTO.builder()
//                .id(id)
//                .name(sparePartName)
//                .build();
//    }
//
//    @Override
//    public void insertAttributes(HttpServletRequest req) {
//        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
//                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
//
//        String filter = req.getParameter(FILTER);
//        String input = req.getParameter(USER_INPUT);
//        List<DeviceTypeDTO> deviceTypes = deviceTypeService.findDeviceTypes();
//
//        ListForPage<SparePartDTO> spareParts;
//        if (input != null && !input.isBlank()) {
//            spareParts = sparePartOrderService.findSpareParts(pageNumber, filter, input);
//        } else {
//            spareParts = sparePartOrderService.findSpareParts(pageNumber);
//        }
//
////        PageManager.insertAttributesForTable(req, spareParts, SPARE_PART_TABLE_PAGE_PATH);
//        req.setAttribute(SHOW_COMMAND, SHOW_SPARE_PART_TABLE);
//        req.setAttribute(DEVICE_TYPES, deviceTypes);
//    }
//
//    @Override
//    public void addParameter(String parameterName, Object parameter) {
//
//    }
//
//    @Override
//    public Object getParameter(String parameterName) {
//        return null;
//    }
//
//    @Override
//    public SparePartDTO getResult() {
//        return sparePart;
//    }
//}
