package it.academy.services.spare_part;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;

import java.util.List;

public interface SparePartOrderService {

//    List<SparePartDTO> findSparePartsByDeviceTypeId(long id);

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
