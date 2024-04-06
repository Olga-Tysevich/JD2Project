package it.academy.dao.spare_parts_order;

import it.academy.dao.DAO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.entities.spare_parts_order.SparePartsOrder;

import java.util.List;

public interface SparePartsOrderDAO extends DAO<SparePartsOrder, Long> {

    List<SparePartsOrder> findSparePartOrdersByRepairId(long id);

}
