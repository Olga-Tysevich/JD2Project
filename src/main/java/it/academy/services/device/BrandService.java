package it.academy.services.device;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.req.BrandDTO;
import it.academy.exceptions.common.AccessDenied;

import java.util.List;

public interface BrandService {

    void createBrand(BrandDTO brand) throws AccessDenied;

    void updateBrand(BrandDTO brand) throws AccessDenied;

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrands();

    ListForPage<BrandDTO> findBrands(int pageNumber);

    ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input);

}
