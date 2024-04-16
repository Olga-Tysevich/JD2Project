package it.academy.services;

import it.academy.dto.req.ChangeSparePartOrderDTO;
import it.academy.dto.resp.SparePartOrderDTO;
import it.academy.dto.resp.ListForPage;

import java.util.List;

public interface SparePartOrderService {

    void addSparePartOrder(SparePartOrderDTO partDTO);

    void changeSparePartOrder(ChangeSparePartOrderDTO partDTO);

    void removeSparePartOrder(long id);

    SparePartOrderDTO findSparePartOrder(long id);

    List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id);

    ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber);

    ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input);

}
