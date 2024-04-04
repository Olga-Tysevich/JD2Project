package it.academy.dao.repair;

import it.academy.dao.DAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairStatus;

import java.util.List;

public interface RepairDAO extends DAO<Repair,Long> {

    List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize);


}
