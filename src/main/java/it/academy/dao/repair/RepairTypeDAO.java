package it.academy.dao.repair;

import it.academy.dao.ComponentDAO;
import it.academy.entities.repair.RepairType;

import java.util.List;

public interface RepairTypeDAO extends ComponentDAO<RepairType, Long> {

    List<RepairType> findAllActive();

}
