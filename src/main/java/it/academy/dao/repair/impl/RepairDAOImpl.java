package it.academy.dao.repair.impl;


import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairStatus;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
}
