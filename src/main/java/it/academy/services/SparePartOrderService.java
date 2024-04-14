package it.academy.services;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.req.SparePartOrderDTO;
import it.academy.dto.resp.ListForPage;

import java.util.List;

public interface SparePartOrderService {

//    List<SparePartDTO> findSparePartsByDeviceTypeId(long id);

    ListForPage<ChangeSparePartDTO> findSpareParts(int pageNumber);

    ListForPage<ChangeSparePartDTO> findSpareParts(int pageNumber, String filter, String input);

    void addSparePartOrder(SparePartOrderDTO partDTO);

    void changeSparePartOrder(SparePartOrderDTO partDTO);

    void removeSparePartOrder(long id);

    SparePartOrderDTO findSparePartOrder(long id);

    List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id);

    ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber);

    ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input);

//    ListForPage<SparePartDTO> findSparePart(ParametersForSearchDTO parameters);

}
