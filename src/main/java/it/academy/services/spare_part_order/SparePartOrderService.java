package it.academy.services.spare_part_order;

import it.academy.dto.spare_part.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part.SparePartOrderDTO;
import it.academy.dto.ListForPage;

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
