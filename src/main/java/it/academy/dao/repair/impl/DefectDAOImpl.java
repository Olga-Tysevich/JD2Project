package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.DefectDAO;
import it.academy.entities.repair.components.Defect;

public class DefectDAOImpl extends DAOImpl<Defect, Long> implements DefectDAO {

    public DefectDAOImpl() {
        super(Defect.class);
    }

}
