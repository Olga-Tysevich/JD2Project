package it.academy.services.device;

import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;

import java.util.List;

public interface BrandService {

    void addBrand(BrandDTO brand);

    void updateBrand(BrandDTO brand);

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrand();

    ListForPage<BrandDTO> findBrands(int pageNumber);

    ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input);
}
