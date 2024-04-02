package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.SparePartsOrderDAO;
import it.academy.entities.repair.spare_parts_order.SparePartsOrder;

public class SparePartsOrderDAOImpl extends DAOImpl<SparePartsOrder, Long> implements SparePartsOrderDAO {

    public SparePartsOrderDAOImpl() {
        super(SparePartsOrder.class);
    }
}
