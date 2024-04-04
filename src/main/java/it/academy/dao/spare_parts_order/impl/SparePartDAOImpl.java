package it.academy.dao.spare_parts_order.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_parts_order.SparePartDAO;
import it.academy.entities.spare_parts_order.SparePart;

public class SparePartDAOImpl extends DAOImpl<SparePart, Long> implements SparePartDAO {

    public SparePartDAOImpl() {
        super(SparePart.class);
    }

}
