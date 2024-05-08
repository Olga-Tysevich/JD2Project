package it.academy.services.spare_part_order;

import it.academy.dto.TablePage2;
import it.academy.dto.spare_part.SparePartDTO;

import java.util.List;

public interface SparePartService {

    void create(SparePartDTO sparePartDTO);

    void update(SparePartDTO sparePartDTO);

    void delete(long id);

    SparePartDTO find(long id);

    TablePage2<SparePartDTO> findForPage(int pageNumber);

    TablePage2<SparePartDTO> findForPageByName(int pageNumber, String input);

    TablePage2<SparePartDTO> findForPageByModelName(int pageNumber, String input);

    List<SparePartDTO> findSparePartsByModelId(long id);

}
