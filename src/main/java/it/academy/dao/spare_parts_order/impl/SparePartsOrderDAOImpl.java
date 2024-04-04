package it.academy.dao.spare_parts_order.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_parts_order.SparePartsOrderDAO;
import it.academy.entities.spare_parts_order.SparePartsOrder;

public class SparePartsOrderDAOImpl extends DAOImpl<SparePartsOrder, Long> implements SparePartsOrderDAO {

    public SparePartsOrderDAOImpl() {
        super(SparePartsOrder.class);
    }
}
