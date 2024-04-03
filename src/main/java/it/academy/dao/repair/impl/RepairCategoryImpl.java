package it.academy.dao.repair.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.repair.RepairCategoryDAO;
import it.academy.entities.repair.components.RepairCategory;

public class RepairCategoryImpl extends DAOImpl<RepairCategory, Long> implements RepairCategoryDAO {

    public RepairCategoryImpl() {
        super(RepairCategory.class);
    }
}
