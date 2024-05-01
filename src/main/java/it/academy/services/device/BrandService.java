package it.academy.services.device;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.TablePage;
import java.util.List;

public interface BrandService {

    void createBrand(BrandDTO brand);

    void updateBrand(BrandDTO brand);

    void deleteBrand(long id);

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrands();

    TablePage<BrandDTO> findBrands(int pageNumber, String filter, String input);

}
