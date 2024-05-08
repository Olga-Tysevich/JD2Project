package it.academy.services.spare_part_order;

import it.academy.dto.TablePage;
import it.academy.dto.spare_part.SparePartDTO;

import java.util.List;

public interface SparePartService {

    void create(SparePartDTO sparePartDTO);

    void update(SparePartDTO sparePartDTO);

    void delete(long id);

    SparePartDTO find(long id);

    TablePage<SparePartDTO> findForPage(int pageNumber);

    TablePage<SparePartDTO> findForPageByName(int pageNumber, String input);

    TablePage<SparePartDTO> findForPageByModelName(int pageNumber, String input);

    List<SparePartDTO> findSparePartsByModelId(long id);

}
