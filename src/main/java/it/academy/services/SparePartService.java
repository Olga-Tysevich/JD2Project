package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;

import java.util.List;

public interface SparePartService {

    void addSparePart(SparePartDTO partDTO);

    void changeSparePart(SparePartDTO partDTO);

    SparePartDTO findSparePart(long id);

    List<SparePartDTO> findSparePartsByDeviceTypeId(long id);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber);

    void addSparePartOrder(SparePartOrderDTO partDTO);

    List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id);

//    ListForPage<SparePartDTO> findSparePart(ParametersForSearchDTO parameters);

}
