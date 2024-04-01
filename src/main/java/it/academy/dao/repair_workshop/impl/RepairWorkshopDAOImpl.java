package it.academy.dao.repair_workshop.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair_workshop.RepairWorkshopDAO;
import it.academy.entities.repair_workshop.RepairWorkshop;

public class RepairWorkshopDAOImpl extends DAOImpl<RepairWorkshop, Long> implements RepairWorkshopDAO {

    public RepairWorkshopDAOImpl() {
        super(RepairWorkshop.class);
    }

}
