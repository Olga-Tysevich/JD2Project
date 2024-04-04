package it.academy.dao.impl;

import it.academy.dao.RepairWorkshopDAO;
import it.academy.entities.repair_workshop.RepairWorkshop;

public class RepairWorkshopDAOImpl extends DAOImpl<RepairWorkshop, Long> implements RepairWorkshopDAO {

    public RepairWorkshopDAOImpl() {
        super(RepairWorkshop.class);
    }

}
