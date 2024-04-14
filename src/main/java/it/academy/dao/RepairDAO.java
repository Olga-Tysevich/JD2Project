package it.academy.dao;

import it.academy.entities.Repair;
import it.academy.utils.enums.RepairStatus;
import java.math.BigInteger;
import java.util.List;

public interface RepairDAO extends DAO<Repair,Long> {

    List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize);

    BigInteger getNumberOfEntriesByStatus(RepairStatus status);

}
