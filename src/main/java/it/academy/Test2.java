package it.academy;

import it.academy.dto.BrandDTO;
import it.academy.dto.ModelDTO;
import it.academy.services.BrandService;
import it.academy.services.ModelService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.services.impl.ModelServiceImpl;

import java.util.List;

public class Test2 {

    public static void main(String[] args) {
//        BrandService brandService = new BrandServiceImpl();
//        List<BrandDTO> brandDTOList = brandService.findBrands();
//        System.out.println();
        ModelService modelService = new ModelServiceImpl();
        List<ModelDTO> models = modelService.findModelsByBrandId(1L);
        System.out.println();
    }

}
