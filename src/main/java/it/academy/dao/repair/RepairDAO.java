package it.academy.dao.repair;

import it.academy.dao.DAO;
import it.academy.entities.repair.Repair;
import it.academy.utils.enums.RepairStatus;
import java.util.List;
import java.util.Map;

public interface RepairDAO extends DAO<Repair,Long> {

//    List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize);
//
//    long getNumberOfEntriesByStatus(RepairStatus status);
//
//    List<Repair> findRepairsByServiceId(long serviceCenterId, int page, int listSize);
//
//    long getNumberOfEntriesByServiceId(long serviceCenterId);
//
//    List<Repair> findRepairsByStatusAndServiceId(long serviceCenterId, RepairStatus status, int page, int listSize);
//
//    long getNumberOfEntriesByStatusAndServiceId(long serviceCenterId, RepairStatus status);
//
//    List<Repair> findRepairsByFilterAndServiceId(long serviceCenterId, int page, int listSize, String filter, String input);
//
//    long getNumberOfEntriesByFilterAndServiceId(long serviceCenterId, String filter, String input);

    List<Repair> findAllByPageAndSearch(Integer currentPage, Integer itemsPerPage, Map<String, String> searchParam);

    long getCountBySearch(Integer currentPage, Integer itemsPerPage, Map<String, String> searchParam);

}
