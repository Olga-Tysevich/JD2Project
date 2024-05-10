package it.academy.services.device;

import it.academy.dto.TablePage;
import it.academy.dto.device.BrandDTO;
import java.util.List;
import java.util.Map;

public interface BrandService {

    BrandDTO create(BrandDTO brand);

    BrandDTO update(BrandDTO brand);

    void delete(long id);

    BrandDTO find(long id);

    List<BrandDTO> findAll();

    TablePage<BrandDTO> findForPage(int pageNumber, Map<String, String> userInput);

}
