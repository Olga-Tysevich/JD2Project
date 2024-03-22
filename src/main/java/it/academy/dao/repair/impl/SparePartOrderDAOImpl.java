package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.SparePartOrderDAO;
import it.academy.entities.repair.spare_part.SparePartsOrder;

public class SparePartOrderDAOImpl extends DAOImpl<SparePartsOrder, Long> implements SparePartOrderDAO {

    public SparePartOrderDAOImpl() {
        super(SparePartsOrder.class);
    }
}
