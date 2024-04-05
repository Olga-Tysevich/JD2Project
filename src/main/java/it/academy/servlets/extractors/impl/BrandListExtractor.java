package it.academy.servlets.extractors.impl;

import it.academy.dto.device.BrandDTO;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.BRANDS;

public class BrandListExtractor implements Extractor {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public void extractValues(HttpServletRequest req) {

        List<BrandDTO> brandDTOList = brandService.findBrands();

        req.setAttribute(BRANDS, brandDTOList);

    }
}
