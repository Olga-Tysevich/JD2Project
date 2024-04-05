package it.academy.servlets.extractors.impl;

import it.academy.dto.device.BrandDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.BRANDS;

public class BrandListExtractor implements Extractor {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public void extractValues(HttpServletRequest req) {

        List<BrandDTO> brandDTOList = repairService.findBrands();

        req.setAttribute(BRANDS, brandDTOList);

    }
}
