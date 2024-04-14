package it.academy.dao;

import it.academy.entities.spare_parts_order.SparePart;

import java.util.List;

public interface SparePartDAO extends DAO<SparePart, Long> {

    List<SparePart> findByDeviceTypeId(long id);
}
