package it.academy.dao.repair;

import it.academy.dao.DAO;
import it.academy.entities.repair.Repair;
import it.academy.utils.enums.RepairStatus;
import java.util.List;

public interface RepairDAO extends DAO<Repair,Long> {

    List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize);

    long getNumberOfEntriesByStatus(RepairStatus status);

    List<Repair> findRepairsByServiceId(long serviceCenterId, int page, int listSize);

    long getNumberOfEntriesByServiceId(long serviceCenterId);

    List<Repair> findRepairsByStatusAndServiceId(long serviceCenterId, RepairStatus status, int page, int listSize);

    long getNumberOfEntriesByStatusAndServiceId(long serviceCenterId, RepairStatus status);

}
