package it.academy.dao.repair.impl;


import it.academy.dao.DAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.entities.repair.Repair;

public class RepairDAOImpl extends DAOImpl<Repair,Long> implements RepairDAO {

    public RepairDAOImpl() {
        super(Repair.class);
    }

}
