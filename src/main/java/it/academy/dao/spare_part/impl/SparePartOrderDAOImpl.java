package it.academy.dao.spare_part.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class SparePartOrderDAOImpl extends DAOImpl<SparePartOrder, Long> implements SparePartOrderDAO {

    public SparePartOrderDAOImpl() {
        super(SparePartOrder.class);
    }

    public SparePartOrderDAOImpl(TransactionManger manger) {
        super(manger, SparePartOrder.class);
    }

    @Override
    public List<SparePartOrder> findSparePartOrdersByRepairId(long id) {
        CriteriaQuery<SparePartOrder> find = criteriaBuilder().createQuery(SparePartOrder.class);
        Root<SparePartOrder> root = find.from(SparePartOrder.class);

        Join<SparePartOrder, Repair> join = root.join(REPAIR);
        find.select(root).where(criteriaBuilder().equal(join.get(OBJECT_ID), id));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }

}
