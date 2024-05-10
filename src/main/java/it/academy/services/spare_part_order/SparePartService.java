package it.academy.services.spare_part_order;

import it.academy.dto.TablePage;
import it.academy.dto.spare_part.SparePartDTO;

import java.util.List;
import java.util.Map;

public interface SparePartService {

    void create(SparePartDTO sparePartDTO);

    void update(SparePartDTO sparePartDTO);

    void delete(long id);

    SparePartDTO find(long id);

    List<SparePartDTO> findSparePartsByModelId(long id);

    TablePage<SparePartDTO> findForPage(int pageNumber, Map<String, String> userInput);

}
