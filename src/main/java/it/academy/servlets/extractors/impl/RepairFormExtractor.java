package it.academy.servlets.extractors.impl;

import it.academy.dto.device.ModelDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class RepairFormExtractor implements Extractor {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public void extractValues(HttpServletRequest req) {

        long modelId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : DEFAULT_ID;
        ModelDTO model = repairService.findModel(modelId);

        req.setAttribute(MODEL, model);

    }

}
