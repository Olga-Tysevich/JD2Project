package it.academy.dao;

import it.academy.entities.SparePartOrder;

import java.util.List;

public interface SparePartOrderDAO extends DAO<SparePartOrder, Long> {

    List<SparePartOrder> findSparePartOrdersByRepairId(long id);

}
