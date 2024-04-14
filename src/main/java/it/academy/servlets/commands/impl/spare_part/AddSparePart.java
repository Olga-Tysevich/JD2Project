//package it.academy.servlets.commands.impl.add;
//
//import it.academy.dto.req.SparePartDTO;
//import it.academy.services.spare_part.SparePartService;
//import it.academy.services.spare_part.impl.SparePartServiceImpl;
//import it.academy.servlets.commands.ActionCommand;
//import it.academy.servlets.extractors.Extractor;
//import it.academy.servlets.extractors.impl.SparePartExtractor;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static it.academy.utils.Constants.DEVICE_TYPE_ID;
//import static it.academy.utils.Constants.MAIN_PAGE_PATH;
//
//public class AddSparePart implements ActionCommand {
//    private SparePartService sparePartService = new SparePartServiceImpl();
//    private Extractor<SparePartDTO> extractor = new SparePartExtractor();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//
//        extractor.extractValues(req);
//        List<Long> idList = getDeviceTypeId(req);
//
//        SparePartDTO sparePartDTO = extractor.getResult();
//        sparePartService.addSparePart(sparePartDTO, idList);
//
//        extractor.insertAttributes(req);
//
//        return MAIN_PAGE_PATH;
//    }
//
//    protected List<Long> getDeviceTypeId(HttpServletRequest req) {
//        List<String> deviceTypeId = List.of(req.getParameterValues(DEVICE_TYPE_ID));
//        deviceTypeId.forEach(System.out::println);
//        if (deviceTypeId.isEmpty()) {
//            return null;
//        }
//        return deviceTypeId.stream()
//                .map(Long::parseLong)
//                .collect(Collectors.toList());
//    }
//}
