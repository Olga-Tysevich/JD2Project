package it.academy.dao.repair.impl;


import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.utils.dao.ParameterManager;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static it.academy.utils.Constants.LIKE_QUERY_PATTERN;
import static it.academy.utils.Constants.REPAIR_STATUS;

public class RepairDAOImpl extends DAOImpl<Repair, Long> implements RepairDAO {

    public RepairDAOImpl() {
        super(Repair.class);
    }


    @Override
    public List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize) {
        CriteriaQuery<Repair> find = criteriaBuilder().createQuery(Repair.class);
        Root<Repair> root = find.from(Repair.class);

        find.select(root)
                .where(criteriaBuilder().equal(root.get("status"), status),
                        criteriaBuilder().equal(root.get("isDeleted"), false));

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
