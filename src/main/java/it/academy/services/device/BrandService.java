package it.academy.services.device;

import it.academy.dto.TablePage;
import it.academy.dto.device.BrandDTO;
import java.util.List;

public interface BrandService {

    BrandDTO create(BrandDTO brand);

    BrandDTO update(BrandDTO brand);

    void delete(long id);

    BrandDTO find(long id);

    List<BrandDTO> findAll();

    TablePage<BrandDTO> findForPage(int pageNumber);

    TablePage<BrandDTO> findForPageByFilter(int pageNumber, String filter, String input);

}
