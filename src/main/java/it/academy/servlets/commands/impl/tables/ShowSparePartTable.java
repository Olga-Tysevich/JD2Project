//package it.academy.servlets.commands.impl.tables;
//
//import it.academy.servlets.commands.ActionCommand;
//import it.academy.servlets.extractors.impl.SparePartExtractor;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static it.academy.utils.Constants.*;
//
//public class ShowSparePartTable implements ActionCommand {
//    private SparePartExtractor extractor = new SparePartExtractor();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//
//        extractor.insertAttributes(req);
//        req.setAttribute(SHOW_COMMAND, SHOW_SPARE_PART_TABLE);
//
//        return MAIN_PAGE_PATH;
//    }
//
//}
