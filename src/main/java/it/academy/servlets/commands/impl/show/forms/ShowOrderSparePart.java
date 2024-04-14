//package it.academy.servlets.commands.impl.forms;
//
//import it.academy.servlets.commands.ActionCommand;
//import it.academy.servlets.extractors.Extractor;
//import it.academy.servlets.extractors.impl.SparePartOrderExtractor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import static it.academy.utils.Constants.*;
//
//public class ShowOrderSparePart implements ActionCommand {
//    private Extractor extractor = new SparePartOrderExtractor();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//
//        extractor.extractValues(req);
//        extractor.insertAttributes(req);
//
//        return SPARE_PART_ORDER_PAGE_PATH;
//    }
//
//}
