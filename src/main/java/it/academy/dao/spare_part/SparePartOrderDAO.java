package it.academy.dao.spare_part;

import it.academy.dao.DAO;
import it.academy.entities.spare_part.SparePartOrder;

import java.util.List;

public interface SparePartOrderDAO extends DAO<SparePartOrder, Long> {

    List<SparePartOrder> findSparePartOrdersByRepairId(long id);

}
