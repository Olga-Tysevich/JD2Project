package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;

import java.util.List;

public interface SparePartOrderService {

    void addSparePart(SparePartDTO partDTO);

    void changeSparePart(SparePartDTO partDTO);

    SparePartDTO findSparePart(long id);

    List<SparePartDTO> findSparePartsByDeviceTypeId(long id);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input);

    void addSparePartOrder(SparePartOrderDTO partDTO);

    void changeSparePartOrder(SparePartOrderDTO partDTO);

    void removeSparePartOrder(long id);

    SparePartOrderDTO findSparePartOrder(long id);

    List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id);

    ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber);

    ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input);

//    ListForPage<SparePartDTO> findSparePart(ParametersForSearchDTO parameters);

}
