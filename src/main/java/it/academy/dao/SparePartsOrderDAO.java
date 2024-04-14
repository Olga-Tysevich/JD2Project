package it.academy.dao;

import it.academy.entities.SparePartsOrder;

import java.util.List;

public interface SparePartsOrderDAO extends DAO<SparePartsOrder, Long> {

    List<SparePartsOrder> findSparePartOrdersByRepairId(long id);

}
