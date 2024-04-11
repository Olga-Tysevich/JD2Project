package it.academy.services.spare_part;

import it.academy.dto.ListForPage;
import it.academy.dto.spare_parts.SparePartDTO;

import java.util.List;

public interface SparePartService {

    void addSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId);

    void updateSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId);

    void deleteSparePart(long id);

    SparePartDTO findSparePart(long id);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber);

    List<SparePartDTO> findSparePartsByDeviceTypeId(long id);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input);


}
