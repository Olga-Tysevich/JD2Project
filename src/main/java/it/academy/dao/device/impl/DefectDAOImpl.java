package it.academy.dao.device.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.device.DefectDAO;
import it.academy.entities.device.components.Defect;

public class DefectDAOImpl extends DAOImpl<Defect, Long> implements DefectDAO {

    public DefectDAOImpl() {
        super(Defect.class);
    }

}
