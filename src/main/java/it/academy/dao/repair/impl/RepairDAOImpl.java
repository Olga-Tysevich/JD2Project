package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.Repair_;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Override
    public List<Repair> findRepairsByFilterAndServiceId(long serviceCenterId, int page, int listSize, String filter, String input) {
        CriteriaQuery<Repair> find = criteriaBuilder().createQuery(Repair.class);
        Root<Repair> repairRoot = find.from(Repair.class);
        Join<Repair, ServiceCenter> join = repairRoot.join(Repair_.SERVICE_CENTER);

        Predicate findPredicate = criteriaBuilder().conjunction();
        findPredicate.getExpressions().add(criteriaBuilder().equal(join.get(ServiceCenter_.ID), serviceCenterId));

        Predicate findByAnyMatch = createLikePredicate(repairRoot, filter, input);

        find.select(repairRoot)
                .where(findPredicate, findByAnyMatch);

        return entityManager().createQuery(find)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntriesByFilterAndServiceId(long serviceCenterId, String filter, String input) {
        CriteriaQuery<Long> find = criteriaBuilder().createQuery(Long.class);
        Root<Repair> repairRoot = find.from(Repair.class);
        Join<Repair, ServiceCenter> join = repairRoot.join(Repair_.SERVICE_CENTER);

        Predicate findPredicate = criteriaBuilder().conjunction();
        findPredicate.getExpressions().add(criteriaBuilder().equal(join.get(ServiceCenter_.ID), serviceCenterId));
        Predicate findByAnyMatch = createLikePredicate(repairRoot, filter, input);

        find.select(criteriaBuilder().count(repairRoot))
                .where(findPredicate, findByAnyMatch);

        return entityManager().createQuery(find)
                .getSingleResult();
    }
}
