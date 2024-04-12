package it.academy.dao.spare_parts_order.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.spare_parts_order.SparePartsOrderDAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_parts_order.SparePartsOrder;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Constants.*;

public class SparePartsOrderDAOImpl extends DAOImpl<SparePartsOrder, Long> implements SparePartsOrderDAO {

    public SparePartsOrderDAOImpl() {
        super(new TransactionManger(), SparePartsOrder.class);
    }

    public SparePartsOrderDAOImpl(TransactionManger manger) {
        super(manger, SparePartsOrder.class);
    }

    @Override
    public List<SparePartsOrder> findSparePartOrdersByRepairId(long id) {
        CriteriaQuery<SparePartsOrder> find = criteriaBuilder().createQuery(SparePartsOrder.class);
        Root<SparePartsOrder> root = find.from(SparePartsOrder.class);

        Join<SparePartsOrder, Repair> join = root.join(REPAIR);
        find.select(root).where(criteriaBuilder().equal(join.get(OBJECT_ID), id));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }

}
