package it.academy.services.spare_part_order;

import it.academy.dto.spare_part.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part.CreateOrderDTO;
import it.academy.dto.spare_part.SparePartOrderDTO;
import it.academy.dto.TablePage;

import java.util.List;

public interface SparePartOrderService {

    void createSparePartOrder(CreateOrderDTO createOrderDTO);

    void changeSparePartOrder(ChangeSparePartOrderDTO partDTO);

    void deleteSparePartOrder(long id);

    SparePartOrderDTO findSparePartOrder(long id);

    List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id);

    TablePage<SparePartOrderDTO> findSparePartOrders(int pageNumber);

    TablePage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input);

}
