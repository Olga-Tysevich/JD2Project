package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.ModelExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();
    private Extractor<ModelDTO> extractor = new ModelExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        ModelDTO modelDTO  = extractor.getResult();
        modelDTO.setIsActive(true);
        modelService.addModel(modelDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}