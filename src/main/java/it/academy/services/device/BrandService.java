package it.academy.services.device;

import it.academy.dto.TablePage2;
import it.academy.dto.device.BrandDTO;
import java.util.List;

public interface BrandService {

    BrandDTO create(BrandDTO brand);

    BrandDTO update(BrandDTO brand);

    void delete(long id);

    BrandDTO find(long id);

    List<BrandDTO> findAll();

    TablePage2<BrandDTO> findForPage(int pageNumber);

    TablePage2<BrandDTO> findForPageByFilter(int pageNumber, String filter, String input);

}
