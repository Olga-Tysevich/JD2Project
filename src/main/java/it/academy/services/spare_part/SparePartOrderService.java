package it.academy.services.spare_part;

import it.academy.dto.req.SparePartDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.repair.spare_parts.SparePartOrderDTO;

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
