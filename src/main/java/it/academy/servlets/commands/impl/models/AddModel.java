//package it.academy.servlets.commands.impl.models;
//
//import it.academy.dto.device.req.ModelDTO;
//import it.academy.services.device.ModelService;
//import it.academy.services.device.impl.ModelServiceImpl;
//import it.academy.servlets.commands.ActionCommand;
//import it.academy.servlets.extractors.Extractor;
////import it.academy.servlets.extractors.impl.ModelExtractor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static it.academy.utils.Constants.MAIN_PAGE_PATH;
//
//public class AddModel implements ActionCommand {
//    private ModelService modelService = new ModelServiceImpl();
////    private Extractor<ModelDTO> extractor = new ModelExtractor();
//
//    @Override
//    public String execute(HttpServletRequest req) {
//
//        extractor.extractValues(req);
//
//        ModelDTO modelDTO  = extractor.getResult();
//        modelDTO.setIsActive(true);
//        modelService.addModel(modelDTO);
//
//        extractor.insertAttributes(req);
//
//        return MAIN_PAGE_PATH;
//    }
//
//}