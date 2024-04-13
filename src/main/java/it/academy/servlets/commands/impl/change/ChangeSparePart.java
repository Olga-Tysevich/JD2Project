//package it.academy.servlets.commands.impl.change;
//
//import it.academy.dto.spare_parts.SparePartDTO;
//import it.academy.services.spare_part.SparePartService;
//import it.academy.services.spare_part.impl.SparePartServiceImpl;
//import it.academy.servlets.commands.impl.add.AddSparePart;
//import it.academy.servlets.extractors.Extractor;
//import it.academy.servlets.extractors.impl.SparePartExtractor;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import static it.academy.utils.Constants.MAIN_PAGE_PATH;
//
//public class ChangeSparePart extends AddSparePart {
//    private SparePartService sparePartService = new SparePartServiceImpl();
//    private Extractor<SparePartDTO> extractor = new SparePartExtractor();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//
//        extractor.extractValues(req);
//        List<Long> idList = getDeviceTypeId(req);
//        SparePartDTO sparePartDTO = extractor.getResult();
//        sparePartService.updateSparePart(sparePartDTO, idList);
//
//        extractor.insertAttributes(req);
//
//        return MAIN_PAGE_PATH;
//    }
//
//}
