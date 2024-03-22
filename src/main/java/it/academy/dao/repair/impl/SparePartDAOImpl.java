package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.SparePartDAO;
import it.academy.entities.repair.spare_part.SparePart;

public class SparePartDAOImpl extends DAOImpl<SparePart, Long> implements SparePartDAO {

    public SparePartDAOImpl() {
        super(SparePart.class);
    }

}
