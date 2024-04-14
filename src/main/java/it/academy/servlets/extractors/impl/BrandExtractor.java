package it.academy.servlets.extractors.impl;

import it.academy.dto.device.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class BrandExtractor  implements Extractor<BrandDTO> {
    private BrandService brandService = new BrandServiceImpl();
    private BrandDTO brand;

    @Override
    public void extractValues(HttpServletRequest req) {
        Long brandId = req.getParameter(BRAND_ID) != null?
                Long.parseLong(req.getParameter(BRAND_ID)) : null;
        String brandName = req.getParameter(BRAND_NAME);
        Boolean isActive = req.getParameter(IS_ACTIVE) != null && Boolean.parseBoolean(req.getParameter(IS_ACTIVE));
        this.brand = BrandDTO.builder()
                .id(brandId)
                .name(brandName)
                .isActive(isActive)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
//        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
//                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
//
//        String filter = req.getParameter(FILTER);
//        String input = req.getParameter(USER_INPUT);
//
//        ListForPage<BrandDTO> brands;
//        if (input != null && !input.isBlank()) {
//            brands = brandService.findBrands(pageNumber, filter, input);
//        } else {
//            brands = brandService.findBrands(pageNumber);
//        }

//        PageManager.insertAttributesForTable(req, brands, BRAND_TABLE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public BrandDTO getResult() {
        return brand;
    }
}