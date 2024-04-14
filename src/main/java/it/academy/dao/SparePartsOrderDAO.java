package it.academy.dao;

import it.academy.entities.spare_parts_order.SparePartsOrder;

import java.util.List;

public interface SparePartsOrderDAO extends DAO<SparePartsOrder, Long> {

    List<SparePartsOrder> findSparePartOrdersByRepairId(long id);

}
