package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.entities.repair.Repair;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class RepairDAOImpl extends DAOImpl<Repair, Long> implements RepairDAO {

    public RepairDAOImpl(TransactionManger manger) {
        super(manger, Repair.class);
    }

    @Override
    public List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize) {
        return entityManager().createQuery(FIND_REPAIRS_BY_STATUS, Repair.class)
                .setParameter(REPAIR_STATUS, status)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<Repair> findRepairsByStatusAndServiceId(long serviceCenterId, RepairStatus status, int page, int listSize) {
        return entityManager().createQuery(FIND_REPAIRS_BY_STATUS_AND_SERVICE_ID, Repair.class)
                .setParameter(REPAIR_STATUS, status)
                .setParameter(OBJECT_ID, serviceCenterId)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntriesByStatusAndServiceId(long serviceCenterId, RepairStatus status) {
        return entityManager().createQuery(GET_NUMBER_OF_REPAIRS_BY_STATUS_AND_SERVICE_ID, Long.class)
                .setParameter(REPAIR_STATUS, status)
                .setParameter(OBJECT_ID, serviceCenterId)
                .getSingleResult();
    }

    @Override
    public long getNumberOfEntriesByStatus(RepairStatus status) {
        return entityManager().createQuery(GET_NUMBER_OF_REPAIRS_BY_STATUS, Long.class)
                .setParameter(REPAIR_STATUS, status)
                .getSingleResult();
    }

    @Override
    public List<Repair> findRepairsByServiceId(long serviceCenterId, int page, int listSize) {
        return entityManager().createQuery(FIND_REPAIRS_BY_SERVICE_ID, Repair.class)
                .setParameter(OBJECT_ID, serviceCenterId)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntriesByServiceId(long serviceCenterId) {
        return entityManager().createQuery(GET_NUMBER_OF_REPAIRS_BY_SERVICE_ID, Long.class)
                .setParameter(OBJECT_ID, serviceCenterId)
                .getSingleResult();
    }

}
