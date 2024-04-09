package it.academy.dao.spare_parts_order;

import it.academy.dao.DAO;
import it.academy.entities.spare_parts_order.SparePart;

import java.util.List;

public interface SparePartDAO extends DAO<SparePart, Long> {

    List<SparePart> findByDeviceTypeId(long id);
}
