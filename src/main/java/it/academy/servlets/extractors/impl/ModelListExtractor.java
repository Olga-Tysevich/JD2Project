package it.academy.servlets.extractors.impl;

import it.academy.dto.device.ModelDTO;
import it.academy.services.ModelService;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ModelListExtractor implements Extractor {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public void extractValues(HttpServletRequest req) {

        long brandId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : DEFAULT_ID;
        List<ModelDTO> models = modelService.findModelsByBrandId(brandId);

        req.setAttribute(MODELS, models);
    }


}
