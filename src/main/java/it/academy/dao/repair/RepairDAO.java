package it.academy.dao.repair;

import it.academy.dao.DAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface RepairDAO extends DAO<Repair,Long> {

    List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize);

    BigInteger getNumberOfEntriesByStatus(RepairStatus status);

}
