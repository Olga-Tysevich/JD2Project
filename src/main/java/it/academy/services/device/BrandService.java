package it.academy.services.device;

import it.academy.dto.req.BrandDTO;
import it.academy.dto.resp.ListForPage;

import java.util.List;

public interface BrandService {

    void createBrand(BrandDTO brand);

    void updateBrand(BrandDTO brand);

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrands();

    ListForPage<BrandDTO> findBrands(int pageNumber);

    ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input);

}
