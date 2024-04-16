package it.academy.dao.impl;


import it.academy.dao.RepairDAO;
import it.academy.entities.Repair;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;

import static it.academy.utils.Constants.*;

public class RepairDAOImpl extends DAOImpl<Repair, Long> implements RepairDAO {

    public RepairDAOImpl() {
        super(Repair.class);
    }

    public RepairDAOImpl(TransactionManger manger) {
        super(manger, Repair.class);
    }

    @Override
    public List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize) {
        CriteriaQuery<Repair> find = criteriaBuilder().createQuery(Repair.class);
        Root<Repair> root = find.from(Repair.class);

        find.select(root)
                .where(criteriaBuilder().equal(root.get(REPAIR_STATUS), status));

        return entityManager()
                .createQuery(find)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public BigInteger getNumberOfEntriesByStatus(RepairStatus status) {
        CriteriaQuery<Long> getNumberOfEntries = criteriaBuilder().createQuery(Long.class);
        Root<Repair> root = getNumberOfEntries.from(Repair.class);

        getNumberOfEntries.select(criteriaBuilder()
                .count(root))
                .where(criteriaBuilder().equal(root.get(REPAIR_STATUS), status));
        long result = entityManager()
                .createQuery(getNumberOfEntries)
                .getSingleResult();
        return new BigInteger(String.valueOf(result));
    }
}
